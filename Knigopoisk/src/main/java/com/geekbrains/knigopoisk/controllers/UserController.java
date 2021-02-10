package com.geekbrains.knigopoisk.controllers;

import com.geekbrains.knigopoisk.controllers.facade.UserControllerApi;
import com.geekbrains.knigopoisk.dto.RoleDto;
import com.geekbrains.knigopoisk.dto.UserDetailsDto;
import com.geekbrains.knigopoisk.dto.UserPasswordDto;
import com.geekbrains.knigopoisk.dto.UserRegistrationDto;
import com.geekbrains.knigopoisk.dto.mappers.RoleMapper;
import com.geekbrains.knigopoisk.dto.mappers.UserMapper;
import com.geekbrains.knigopoisk.entities.Role;
import com.geekbrains.knigopoisk.entities.User;
import com.geekbrains.knigopoisk.exceptions.RoleAttributeNotValidException;
import com.geekbrains.knigopoisk.exceptions.UserAlreadyExistsException;
import com.geekbrains.knigopoisk.exceptions.UserAttributeNotValidException;
import com.geekbrains.knigopoisk.services.contracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController implements UserControllerApi {
    private final UserService userService;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

    @Override
    public List<UserDetailsDto> getAllUser() {
        List<UserDetailsDto> userDtoList = userMapper.getUserDetailsDtoListFromUserList(userService.getAll());
        return userDtoList;
    }

    @Override
    public UserDetailsDto getUser(@NotNull Long id) {
        User user = userService.findByUserId(id);
        return userMapper.getUserDetailsDtoFromUser(user);
    }

    @Override
    public void deleteUserById(@PathVariable("id") @NotNull Long id) {
        userService.deleteByUserId(id);
    }

    // Binding Result после @ValidModel !!!
    @Override
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@Valid @RequestBody UserRegistrationDto userRegistrationDto, BindingResult theBindingResult) {
        if (theBindingResult.hasErrors()) {
            throw new UserAttributeNotValidException("Ошибка валидации", theBindingResult);
        }

        String userName = userRegistrationDto.getUserName();
        if (userService.isUserByNameExists(userName)) {
            throw new UserAlreadyExistsException("Пользователь с таким именем уже существует");
        }

        userService.save(userRegistrationDto);
    }

    @Override
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void update(@Valid UserDetailsDto userDetailsDto, BindingResult theBindingResult) {
        if (theBindingResult.hasErrors()) {
            throw new UserAttributeNotValidException("Ошибка валидации", theBindingResult);
        }

        userService.updateUserDetailsFromUserDetailsDto(userDetailsDto);
    }

    @Override
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void changePassword(@Valid UserPasswordDto userPasswordDto, BindingResult theBindingResult, @NotNull @PathVariable Long id) {
        if (theBindingResult.hasErrors()) {
            throw new UserAttributeNotValidException("Ошибка валидации", theBindingResult);
        }

        userService.updateUserPasswordFromUserPasswordDto(id, userPasswordDto);
    }

    @Override
    public List<RoleDto> getAssignedRoles(@NotNull @PathVariable Long id) {
        return roleMapper.getRoleDtoListFromRoleList(userService.getAssignedRolesByUserId(id));
    }

    @Override
    public List<RoleDto> getUnAssignedRoles(@NotNull @PathVariable Long id) {
        return roleMapper.getRoleDtoListFromRoleList(userService.getUnAssignedRolesByUserId(id));
    }

    @Override
    public List<RoleDto> addRole(@Valid RoleDto roleDto, BindingResult theBindingResult, @NotNull @PathVariable Long id) {
        if (theBindingResult.hasErrors()) {
            throw new RoleAttributeNotValidException("Ошибка валидации роли", theBindingResult);
        }

        List<Role> userRoles = userService.addRoleByRoleName(id, roleDto.getName());
        return roleMapper.getRoleDtoListFromRoleList(userRoles);
    }

    @Override
    public List<RoleDto> removeRole(@Valid RoleDto roleDto, BindingResult theBindingResult, @NotNull @PathVariable Long id) {
        if (theBindingResult.hasErrors()) {
            throw new RoleAttributeNotValidException("Ошибка валидации роли", theBindingResult);
        }

        List<Role> userRoles = userService.removeRoleByRoleName(id, roleDto.getName());
        return roleMapper.getRoleDtoListFromRoleList(userRoles);
    }
}

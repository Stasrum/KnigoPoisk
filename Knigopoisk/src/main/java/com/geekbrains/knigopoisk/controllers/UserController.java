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
import com.geekbrains.knigopoisk.exceptions.*;
import com.geekbrains.knigopoisk.services.contracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController implements UserControllerApi {
    private final UserService userService;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

    @Override
    public UserDetailsDto getUserProfile(@NotNull Principal principal) {
        if (principal == null) {
            throw new UserNotAuthorizedException("User is not authorized");
        }

        String userName = principal.getName();
        User user = userService.findByUserName(userName);
        return userMapper.getUserDetailsDtoFromUser(user);
    }

    @Override
    public UserDetailsDto getUser(@NotNull Long id) {
        User user = userService.findByUserId(id);
        return userMapper.getUserDetailsDtoFromUser(user);
    }

    // Binding Result после @ValidModel !!!
    @Override
    @ResponseStatus(HttpStatus.CREATED)
    public UserDetailsDto register(@Valid @RequestBody UserRegistrationDto userRegistrationDto, BindingResult theBindingResult) {
        if (theBindingResult.hasErrors()) {
            throw new UserAttributeNotValidException("Ошибка валидации", theBindingResult);
        }

        String userName = userRegistrationDto.getUserName();
        if (userService.isUserByNameExists(userName)) {
            throw new UserAlreadyExistsException("Пользователь с таким именем уже существует");
        }

        return userMapper.getUserDetailsDtoFromUser(userService.save(userRegistrationDto));
    }

    @Override
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserDetailsDto update(@Valid UserDetailsDto userDetailsDto, BindingResult theBindingResult, @NotNull Principal principal) {
        if (theBindingResult.hasErrors()) {
            throw new UserAttributeNotValidException("Ошибка валидации", theBindingResult);
        }

        if (principal == null || !userDetailsDto.getUserName().equals(principal.getName())) {
            throw new AccessDeniedException("Изменения других пользователей не допускается");
        }

        User updatedUser = userService.updateUserDetailsFromUserDetailsDto(userDetailsDto);

        return userMapper.getUserDetailsDtoFromUser(updatedUser);
    }

    @Override
    public ResponseEntity<ApiMessage> changePassword(@Valid UserPasswordDto userPasswordDto, BindingResult theBindingResult, @NotNull @PathVariable Long id) {
        if (theBindingResult.hasErrors()) {
            throw new UserAttributeNotValidException("Ошибка валидации", theBindingResult);
        }

        userService.updateUserPasswordFromUserPasswordDto(id, userPasswordDto);

        return new ResponseEntity<>(new ApiMessage("Пароль успешно изменён"), HttpStatus.ACCEPTED);
    }

    @Override
    public List<RoleDto> getAssignedRoles(@NotNull @PathVariable Long id) {
        return roleMapper.getRoleDtoListFromRoleList(userService.getAssignedRolesByUserId(id));
    }

    @Override
    public List<RoleDto> getUnAssignedRoles(@NotNull @PathVariable Long id) {
        return roleMapper.getRoleDtoListFromRoleList(userService.getUnAssignedRolesByUserId(id));
    }

    //=> AdminController
    /*@Override
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
    }*/
}

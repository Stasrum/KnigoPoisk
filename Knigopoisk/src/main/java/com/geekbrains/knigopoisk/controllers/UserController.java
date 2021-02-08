package com.geekbrains.knigopoisk.controllers;

import com.geekbrains.knigopoisk.controllers.facade.UserControllerApi;
import com.geekbrains.knigopoisk.dto.UserDetailsDto;
import com.geekbrains.knigopoisk.dto.UserPasswordDto;
import com.geekbrains.knigopoisk.dto.UserRegistrationDto;
import com.geekbrains.knigopoisk.dto.mappers.UserMapper;
import com.geekbrains.knigopoisk.entities.User;
import com.geekbrains.knigopoisk.exceptions.UserAlreadyExistsException;
import com.geekbrains.knigopoisk.exceptions.UserAttributeNotValidException;
import com.geekbrains.knigopoisk.services.contracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserController implements UserControllerApi {
    private UserService userService;
    @Autowired
    private UserMapper mapper;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<UserDetailsDto> getAllUser() {
        List<UserDetailsDto> userDtoList = mapper.getUserDetailsDtoListFromUserList(userService.getAll());
        return userDtoList;
    }

    @Override
    public UserDetailsDto getUser(@NotNull Long id) {
        User user = userService.findByUserId(id);
        return mapper.getUserDetailsDtoFromUser(user);
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
    public void update(@Valid UserDetailsDto userDetailsDto, BindingResult theBindingResult, @NotNull @PathVariable Long id) {
        if (theBindingResult.hasErrors()) {
            throw new UserAttributeNotValidException("Ошибка валидации", theBindingResult);
        }

        userService.updateUserDetailsFromUserDetailsDto(id, userDetailsDto);
    }

    @Override
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void changePassword(@Valid UserPasswordDto userPasswordDto, BindingResult theBindingResult, @NotNull @PathVariable Long id) {
        if (theBindingResult.hasErrors()) {
            throw new UserAttributeNotValidException("Ошибка валидации", theBindingResult);
        }

        userService.updateUserPasswordFromUserPasswordDto(id, userPasswordDto);
    }
}

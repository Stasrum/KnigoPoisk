package com.geekbrains.knigopoisk.controllers;

import com.geekbrains.knigopoisk.controllers.facade.UserControllerApi;
import com.geekbrains.knigopoisk.dto.UserDto;
import com.geekbrains.knigopoisk.entities.User;
import com.geekbrains.knigopoisk.exceptions.UserAlreadyExistsException;
import com.geekbrains.knigopoisk.exceptions.UserAttributeNotValidException;
import com.geekbrains.knigopoisk.services.contracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

@RestController
public class UserController implements UserControllerApi {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<User> getAllUser() {
        return userService.getAll();
    }

    @Override
    public void deleteUserById(@PathVariable("id") @NotNull Long id) {
        userService.deleteByUserId(id);
    }

    // Binding Result после @ValidModel !!!
    @Override
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@Valid @RequestBody UserDto userDto, BindingResult theBindingResult) {
        if (theBindingResult.hasErrors()) {
            throw new UserAttributeNotValidException("Ошибка валидации", theBindingResult);
        }

        String userName = userDto.getUserName();
        User existingUser = userService.findByUserName(userName);
        if (existingUser != null) {
            throw new UserAlreadyExistsException("Пользователь с таким именем уже существует");
        }

        userService.save(userDto);
    }

    @Override
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void edit(@Valid UserDto userDto, BindingResult theBindingResult) {
        theBindingResult.getAllErrors().removeIf(error ->
                error instanceof FieldError && Arrays.stream(new String[]{"password", "matchingPassword"})
                        .anyMatch(fieldName -> ((FieldError) error).getField().equals(fieldName))
        );

        if (theBindingResult.hasErrors()) {
            throw new UserAttributeNotValidException("Ошибка валидации", theBindingResult);
        }

        Long userId = userDto.getId();
        User existingUser = userService.findByUserId(userId).orElseThrow();

        userService.save(userDto);
    }

    @Override
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void changePassword(@Valid UserDto userDto, BindingResult theBindingResult) {

    }
}

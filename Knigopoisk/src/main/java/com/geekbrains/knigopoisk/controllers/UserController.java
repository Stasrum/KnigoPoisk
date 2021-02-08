package com.geekbrains.knigopoisk.controllers;

import com.geekbrains.knigopoisk.controllers.facade.UserControllerApi;
import com.geekbrains.knigopoisk.dto.UserDto;
import com.geekbrains.knigopoisk.entities.User;
import com.geekbrains.knigopoisk.exceptions.UserAlreadyExistsException;
import com.geekbrains.knigopoisk.exceptions.UserAttributeNotValidException;
import com.geekbrains.knigopoisk.dto.mappers.UserMapper;
import com.geekbrains.knigopoisk.services.contracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController implements UserControllerApi {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private UserMapper mapper;

    @Override
    public List<UserDto> getAllUser() {
        List<UserDto> userDtoList = mapper.getUserDtoListFromUserList(userService.getAll());
        return userDtoList;
    }

    @Override
    public UserDto getUser(@NotNull Long id) {
        User user = userService.findByUserId(id).orElseThrow();
        UserDto userDto = mapper.getUserDtoFromUser(user);
        return userDto;
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
            throw new UserAttributeNotValidException("Ошибка валидации", theBindingResult.getAllErrors());
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
    public void update(@Valid UserDto userDto, BindingResult theBindingResult) {
        List<ObjectError> errors = theBindingResult.getAllErrors().stream()
                .filter(errObj -> (!(errObj instanceof FieldError)) || Arrays.stream(new String[]{"password", "matchingPassword"})
                        .noneMatch(fieldName -> ((FieldError) errObj).getField().equals(fieldName)))
                .collect(Collectors.toList());

        if (errors.size() > 0) {
            throw new UserAttributeNotValidException("Ошибка валидации", errors);
        }

        userService.updateUserDetailsFromUserDto(userDto);
    }

    @Override
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void changePassword(@Valid UserDto userDto, BindingResult theBindingResult) {
        List<ObjectError> errors = theBindingResult.getAllErrors().stream()
                .filter(errObj -> errObj instanceof FieldError && Arrays.stream(new String[]{"password", "matchingPassword"})
                        .anyMatch(fieldName -> ((FieldError) errObj).getField().equals(fieldName)))
                .collect(Collectors.toList());

        if (errors.size() > 0) {
            throw new UserAttributeNotValidException("Ошибка валидации", errors);
        }

        userService.updateUserPasswordFromUserDto(userDto);
    }
}

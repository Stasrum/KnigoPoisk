package com.geekbrains.knigopoisk.controllers;

import com.geekbrains.knigopoisk.api.ApiError;
import com.geekbrains.knigopoisk.controllers.facade.RegistrationControllerApi;
import com.geekbrains.knigopoisk.entities.User;
import com.geekbrains.knigopoisk.entities.UserDto;
import com.geekbrains.knigopoisk.services.contracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class RegistrationController implements RegistrationControllerApi {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    // Binding Result после @ValidModel !!!
    @Override
    @RequestMapping("/register")
    public ApiError register(@Valid @RequestBody UserDto userDto, BindingResult theBindingResult) {
        if (theBindingResult.hasErrors()) {
            return new ApiError(HttpStatus.BAD_REQUEST, "Ошибки в полях", theBindingResult.getFieldErrors().stream()
                    .map(fe -> fe.getField() + " - " + fe.getDefaultMessage()).collect(Collectors.toList()));
        }

        String userName = userDto.getUserName();
        User existingUser = userService.findByUserName(userName);
        if (existingUser != null) {
            return new ApiError(HttpStatus.BAD_REQUEST, "Пользователь с таким логином уже существует", "");
        }

        userService.save(userDto);

        return new ApiError(HttpStatus.OK, "Регистрация успешно завершена", Collections.EMPTY_LIST);
    }
}

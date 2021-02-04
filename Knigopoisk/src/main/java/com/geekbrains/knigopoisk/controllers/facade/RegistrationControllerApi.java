package com.geekbrains.knigopoisk.controllers.facade;

import com.geekbrains.knigopoisk.entities.User;
import com.geekbrains.knigopoisk.entities.UserDto;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

public interface RegistrationControllerApi {
    @InitBinder
    void initBinder(WebDataBinder dataBinder);

    @GetMapping("/registrationForm")
    UserDto showMyLoginPage(Model theModel);

    @PostMapping("/processRegistrationForm")
    User processRegistrationForm(@Valid @ModelAttribute("userDto") UserDto theUserDto, BindingResult theBindingResult);
}

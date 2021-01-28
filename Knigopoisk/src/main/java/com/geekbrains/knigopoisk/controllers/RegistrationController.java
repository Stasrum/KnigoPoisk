package com.geekbrains.knigopoisk.controllers;

import com.geekbrains.knigopoisk.controllers.facade.RegistrationControllerApi;
import com.geekbrains.knigopoisk.entities.User;
import com.geekbrains.knigopoisk.entities.UserDto;
import com.geekbrains.knigopoisk.services.contracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @Override
    public String showMyLoginPage(Model theModel) {
        theModel.addAttribute("userDto", new UserDto());
        return "registration-form";
    }

    // Binding Result после @ValidModel !!!
    @Override
    public String processRegistrationForm(@Valid @ModelAttribute("userDto") UserDto theUserDto,
                                          BindingResult theBindingResult, Model model) {
        if (theBindingResult.hasErrors()) {
            return "registration-form";
        }
        String userName = theUserDto.getUserName();
        User existing = userService.findByUserName(userName);
        if (existing != null) {
            // theSystemUser.setUserName(null);
            model.addAttribute("userDto", theUserDto);
            model.addAttribute("registrationError", "Пользователь с таким именем уже существует");
            return "registration-form";
        }

        userService.save(theUserDto);

        return "registration-confirmation";
    }
}

package com.geekbrains.knigopoisk.controllers;

import com.geekbrains.knigopoisk.entities.User;
import com.geekbrains.knigopoisk.entities.UserDto;
import com.geekbrains.knigopoisk.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/registrationForm")
    public String showMyLoginPage(Model theModel) {
        theModel.addAttribute("userDto", new UserDto());
        return "registration-form";
    }

    // Binding Result после @ValidModel !!!
    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm(@Valid @ModelAttribute("userDto") UserDto requestUserDto,
                                          BindingResult theBindingResult, Model model) {
        if (theBindingResult.hasErrors()) {
            return "registration-form";
        }
        String userName = requestUserDto.getUserName();
        User existing = userService.findByUserName(userName);
        if (existing != null) {
            // theSystemUser.setUserName(null);
            model.addAttribute("userDto", requestUserDto);
            model.addAttribute("registrationError", "Пользователь с таким именем уже существует");
            return "registration-form";
        }

        userService.save(requestUserDto);

        return "registration-confirmation";
    }
}

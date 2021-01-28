package com.geekbrains.knigopoisk.controllers.facade;

import com.geekbrains.knigopoisk.entities.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public interface RegistrationControllerApi {
    @InitBinder
    void initBinder(WebDataBinder dataBinder);

    @GetMapping("/registrationForm")
    String showMyLoginPage(Model theModel);

    @PostMapping("/processRegistrationForm")
    String processRegistrationForm(@Valid @ModelAttribute("userDto") UserDto theUserDto, BindingResult theBindingResult, Model model);
}

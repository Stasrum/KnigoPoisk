package com.geekbrains.knigopoisk.controllers;

import com.geekbrains.knigopoisk.entities.SystemUser;
import com.geekbrains.knigopoisk.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/register")
public class RegistrationRestController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getRegistrationUser")
    public SystemUser showMyLoginPage() {
        return new SystemUser();
    }

    // Binding Result после @ValidModel !!!
    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm(@Valid @ModelAttribute("systemUser") SystemUser theSystemUser,
                                          BindingResult bindingResult) {
        /*if (bindingResult.hasErrors()) {
            return bindingResult.;
        }
        String userName = theSystemUser.getUserName();
        User existing = userService.findByUserName(userName);
        if (existing != null) {
            // theSystemUser.setUserName(null);
            model.addAttribute("systemUser", theSystemUser);
            model.addAttribute("registrationError", "Пользователь с таким именем уже существует");
            return "registration-form";
        }
        userService.save(theSystemUser);*/

        return "registration-confirmation";
    }
}

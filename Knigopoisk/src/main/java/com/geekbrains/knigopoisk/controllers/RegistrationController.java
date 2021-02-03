package com.geekbrains.knigopoisk.controllers;

import com.geekbrains.knigopoisk.controllers.facade.RegistrationControllerApi;
import com.geekbrains.knigopoisk.entities.User;
import com.geekbrains.knigopoisk.entities.UserDto;
import com.geekbrains.knigopoisk.services.contracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/register")
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
    @RequestMapping("/form")
    public UserDto showMyLoginPage(Model theModel) {
        return new UserDto();
    }

    // Binding Result после @ValidModel !!!
    @Override
    @RequestMapping("/process")
    public User processRegistrationForm(@Valid @ModelAttribute("userDto") UserDto theUserDto, BindingResult theBindingResult) {

        if (theBindingResult.hasErrors()) {
//            return new ApiError(HttpStatus.BAD_REQUEST, "ошибки в полях", theBindingResult.getFieldErrors().stream()
//                    .map(fe -> fe.getField() + " - " + fe.getDefaultMessage()).collect(Collectors.toList()));
//            throw new MethodArgumentNotValidException( ,theBindingResult);
        }

        String userName = theUserDto.getUserName();
        User existingUser = userService.findByUserName(userName);
        if (existingUser != null) {
//            return new ApiError(HttpStatus.BAD_REQUEST, "уже существует", "");
        }

        userService.save(theUserDto);

        return existingUser;
    }
}

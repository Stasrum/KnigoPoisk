package com.geekbrains.knigopoisk.controllers.facade;

import com.geekbrains.knigopoisk.api.ApiError;
import com.geekbrains.knigopoisk.entities.UserDto;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface RegistrationControllerApi {
    @InitBinder
    void initBinder(WebDataBinder dataBinder);

    @PostMapping("/process")
    ApiError register(@Valid @RequestBody UserDto userDto, BindingResult theBindingResult);
}

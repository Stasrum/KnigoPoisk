package com.geekbrains.knigopoisk.controllers.facade;

import com.geekbrains.knigopoisk.entities.User;
import com.geekbrains.knigopoisk.dto.UserDto;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface UserControllerApi {

    @GetMapping("/users")
    List<User> getAllUser();

    @GetMapping("/users/delete/{id}")
    void deleteUserById(@PathVariable("id") @NotNull Long id);

    @PostMapping("/users/register")
    void register(@Valid @RequestBody UserDto userDto, BindingResult theBindingResult);
}

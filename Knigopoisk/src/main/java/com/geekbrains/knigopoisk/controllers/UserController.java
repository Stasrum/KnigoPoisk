package com.geekbrains.knigopoisk.controllers;

import com.geekbrains.knigopoisk.controllers.facade.UserControllerApi;
import com.geekbrains.knigopoisk.entities.User;
import com.geekbrains.knigopoisk.services.contracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
public class UserController implements UserControllerApi {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @Override
    public List<User> getAllUser(){
        return userService.getAll();
    }

    @Override
    public void deleteUserById(@PathVariable("id") @NotNull Long id){
        userService.deleteByUserId(id);
    }
}

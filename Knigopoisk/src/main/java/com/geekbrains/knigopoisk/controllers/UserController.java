package com.geekbrains.knigopoisk.controllers;

import com.geekbrains.knigopoisk.entities.User;
import com.geekbrains.knigopoisk.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping()
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/allusers")
    public List<User> getAllUser(){
        return userService.getAll();
    }

    @GetMapping("/deluser/{id}")
    public void deleteUserById(@PathVariable("id") Long id){
        userService.deleteByUserId(id);
    }
}

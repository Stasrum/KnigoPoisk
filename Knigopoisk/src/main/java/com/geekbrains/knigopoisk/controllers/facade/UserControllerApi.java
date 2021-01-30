package com.geekbrains.knigopoisk.controllers.facade;

import com.geekbrains.knigopoisk.entities.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping()
public interface UserControllerApi {

    @GetMapping("/allusers")
    List<User> getAllUser();

    @GetMapping("/deluser/{id}")
    void deleteUserById(@PathVariable("id") Long id);
}

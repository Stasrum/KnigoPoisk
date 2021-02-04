package com.geekbrains.knigopoisk.controllers.facade;

import com.geekbrains.knigopoisk.entities.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface UserControllerApi {

    @GetMapping("/allusers")
    List<User> getAllUser();

    @GetMapping("/deluser/{id}")
    void deleteUserById(@PathVariable("id") @NotNull Long id);
}

package com.geekbrains.knigopoisk.services;

import com.geekbrains.knigopoisk.entities.User;
import com.geekbrains.knigopoisk.entities.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User findByUserName(String userName);

    User save(UserDto userDto);

    boolean save(User user);

    boolean deleteByUserName(String userName);

    List<User> getAll();

}

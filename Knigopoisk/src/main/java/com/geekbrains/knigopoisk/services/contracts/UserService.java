package com.geekbrains.knigopoisk.services.contracts;

import com.geekbrains.knigopoisk.entities.User;
import com.geekbrains.knigopoisk.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService extends UserDetailsService {
    User findByUserName(String userName);

    @Transactional
    User save(UserDto userDto);

    @Transactional
    boolean save(User user);

    @Transactional
    boolean deleteByUserName(String userName);

    @Transactional
    boolean deleteByUserId(Long id);

    @Transactional
    List<User> getAll();
}

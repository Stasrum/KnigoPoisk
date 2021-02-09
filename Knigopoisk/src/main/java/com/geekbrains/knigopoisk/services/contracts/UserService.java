package com.geekbrains.knigopoisk.services.contracts;

import com.geekbrains.knigopoisk.dto.UserDetailsDto;
import com.geekbrains.knigopoisk.dto.UserPasswordDto;
import com.geekbrains.knigopoisk.dto.UserRegistrationDto;
import com.geekbrains.knigopoisk.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService extends UserDetailsService {
    @Transactional
    User findByUserId(Long userId);

    @Transactional
    User findByUserName(String userName);

    @Transactional
    User save(UserRegistrationDto userRegistrationDto);

    @Transactional
    boolean updateUserDetailsFromUserDetailsDto(UserDetailsDto userDetailsDto);

    @Transactional
    boolean updateUserPasswordFromUserPasswordDto(Long userId, UserPasswordDto userPasswordDto);

    @Transactional
    boolean save(User user);

    @Transactional
    boolean deleteByUserName(String userName);

    @Transactional
    boolean deleteByUserId(Long id);

    @Transactional
    List<User> getAll();

    @Transactional
    boolean isUserByNameExists(String userName);
}

package com.geekbrains.knigopoisk.services;

import com.geekbrains.knigopoisk.entities.Role;
import com.geekbrains.knigopoisk.entities.User;
import com.geekbrains.knigopoisk.repositories.RoleService;
import com.geekbrains.knigopoisk.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleService roleService;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private final String
            ADMIN_USERNAME = "admin@mail.com",
            ADMIN_PASSWORD_ENCODED = "$2a$10$ts2FXg1jVvuuEIJqIItTB.Ra1ZklHcSYyrnt3AkGVJkekFUjWcu9K",
            ADMIN_PASSWORD_DECODED = "1234",
            USER_USERNAME = "user@mail.com",
            USER_PASSWORD_ENCODED = "$2a$10$ts2FXg1jVvuuEIJqIItTB.Ra1ZklHcSYyrnt3AkGVJkekFUjWcu9K",
            USER_PASSWORD_DECODED = "1234",
            BAD_USERNAME = "baduser@mail.com",
            BAD_PASSWORD = "12345";

    private final User user, admin;

    public UserServiceTest() {
        user = new User(
                1L,
                USER_USERNAME,
                USER_PASSWORD_ENCODED,
                Collections.singletonList(new Role(0L, "USER")),
                true,
                true,
                true,
                true,
                "Ivan",
                "Ivanov",
                new Date()
        );

        admin = new User(
                2L,
                ADMIN_USERNAME,
                ADMIN_PASSWORD_ENCODED,
                Collections.singletonList(new Role(1L, "ADMIN")),
                true,
                true,
                true,
                true,
                "Петр",
                "Петров",
                new Date()
        );

        MockitoAnnotations.openMocks(this);
        when(userRepository.findUserByUsername(USER_USERNAME))
                .thenReturn(user);
        when(userRepository.findUserByUsername(ADMIN_USERNAME))
                .thenReturn(admin);

        when(roleService.getRoleByName("USER"))
                .thenReturn(new Role("USER"));

        when((passwordEncoder.encode(USER_PASSWORD_DECODED)))
                .thenReturn(USER_PASSWORD_ENCODED);
        when((passwordEncoder.encode(ADMIN_PASSWORD_DECODED)))
                .thenReturn(ADMIN_PASSWORD_ENCODED);
    }

    @Test
    @DisplayName("Loading user by username is success")
    void loadUserByUsernameSuccessTest() {
        UserDetails user = userService.loadUserByUsername(USER_USERNAME);
        Assertions.assertEquals(USER_PASSWORD_ENCODED, user.getPassword());
    }

    @Test
    @DisplayName("Loading user by username is failed")
    void loadUserByUsernameFailTest() {
        Assertions.assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername(BAD_USERNAME));
    }
}
package com.geekbrains.knigopoisk.services;

import com.geekbrains.knigopoisk.configs.Roles;
import com.geekbrains.knigopoisk.entities.User;
import com.geekbrains.knigopoisk.repositories.UserRepository;
import com.geekbrains.knigopoisk.services.contracts.RoleService;
import com.geekbrains.knigopoisk.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static com.geekbrains.knigopoisk.testUtils.Users.*;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleService roleService;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    private final User user, admin;

    public UserServiceTest() {
        user = getUser();

        admin = getAdmin();

        MockitoAnnotations.openMocks(this);
        when(userRepository.findUserByUsername(USER_USERNAME))
                .thenReturn(user);
        when(userRepository.findUserByUsername(ADMIN_USERNAME))
                .thenReturn(admin);

        when(roleService.getRoleByName(Roles.ROLE_USER.name()))
                .thenReturn(getRoleUser());

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

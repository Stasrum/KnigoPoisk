package com.geekbrains.knigopoisk.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geekbrains.knigopoisk.configs.JWTTokenUtils;
import com.geekbrains.knigopoisk.configs.Roles;
import com.geekbrains.knigopoisk.dto.JwtRequest;
import com.geekbrains.knigopoisk.entities.Role;
import com.geekbrains.knigopoisk.entities.User;
import com.geekbrains.knigopoisk.repositories.UserRepository;
import com.geekbrains.knigopoisk.services.contracts.RoleService;
import com.geekbrains.knigopoisk.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static com.geekbrains.knigopoisk.testUtils.Users.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleService roleService;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @InjectMocks
    private JWTTokenUtils jwtTokenUtils;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long jwtExpirationTime;

    private final User user, admin;

    public AuthControllerTest() {
        user = getUser();
        admin = getAdmin();

        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("User authorization test")
    @Disabled
    public void userAuthorizationTest() throws Exception {
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

        JwtRequest jwtRequest = new JwtRequest();
        jwtRequest.setUsername(USER_USERNAME);
        jwtRequest.setPassword(USER_PASSWORD_DECODED);
        ObjectMapper mapper = new ObjectMapper();
        String jwtRequestStr = mapper.writeValueAsString(jwtRequest);
        mockMvc
                .perform(post("/auth")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(jwtRequestStr)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }
}
package com.geekbrains.knigopoisk.configs;

import com.geekbrains.knigopoisk.configs.Roles;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static com.geekbrains.knigopoisk.testUtils.Users.*;

class JWTTokenUtilsTest {

    private final String TEST_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImV4cCI6MTYxMjg0OTc5OSwiaWF0IjoxNjEyMjQwOTk5fQ.F37-sc72OjW4NLvJz3ZHJlo1t6VXHogpv3C2HXYU2Vs";

    private UserDetails user;
    private List<SimpleGrantedAuthority> authorities;

    @InjectMocks
    private JWTTokenUtils jwtTokenUtils;

    @BeforeEach
    void init(){
        jwtTokenUtils = new JWTTokenUtils();
        ReflectionTestUtils.setField(jwtTokenUtils, "secret", "SecretWord");
        ReflectionTestUtils.setField(jwtTokenUtils, "jwtExpirationTime", 608800000);
        authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        user = new User(
                USER_USERNAME,
                USER_PASSWORD_ENCODED,
                true,
                true,
                true,
                true,
                authorities
        );

    }

    @Test
    @Disabled
    void generateToken() {
        String token = jwtTokenUtils.generateToken(user);
        Assertions.assertEquals(TEST_TOKEN, token);
    }

    @Test
    void getUsernameFromToken() {
        String username = jwtTokenUtils.getUsernameFromToken(TEST_TOKEN);
        Assertions.assertEquals(USER_USERNAME, username);
    }

    @Test
    void getRoles() {
        List<String> roles = jwtTokenUtils.getRoles(TEST_TOKEN);
        Assertions.assertEquals(Roles.ROLE_USER.name(), roles.get(0));
    }
}
package com.geekbrains.knigopoisk.configs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

class JWTTokenUtilsTest {

    private final String USERNAME = "user1";
    private final String PASSWORD = "password1";
    private final String TEST_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMSIsInJvbGVzIjpbIkFETUlOIl0sImV4cCI6MTYxMjI5MTI1MywiaWF0IjoxNjExNjgyNDUzfQ.SqjK-57BzKzE58kegyj4NCLmn_kNvOfTFYMp0_BehnU";

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
        authorities.add(new SimpleGrantedAuthority("ADMIN"));
        user = new User(
                USERNAME,
                PASSWORD,
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
        Assertions.assertEquals(USERNAME, username);
    }

    @Test
    void getRoles() {
        List<String> roles = jwtTokenUtils.getRoles(TEST_TOKEN);
        Assertions.assertEquals("ADMIN", roles.get(0));
    }
}
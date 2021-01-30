//package com.geekbrains.knigopoisk.controllers;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.geekbrains.knigopoisk.configs.JWTTokenUtils;
//import com.geekbrains.knigopoisk.dto.JwtRequest;
//import com.geekbrains.knigopoisk.entities.Role;
//import com.geekbrains.knigopoisk.entities.User;
//import com.geekbrains.knigopoisk.repositories.RoleService;
//import com.geekbrains.knigopoisk.repositories.UserRepository;
//import com.geekbrains.knigopoisk.services.UserService;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.provider.ValueSource;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.util.LinkedMultiValueMap;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.util.Collections;
//import java.util.Date;
//import java.util.Map;
//
//import static org.hamcrest.Matchers.containsString;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class AuthControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private RoleService roleService;
//
//    @Mock
//    private BCryptPasswordEncoder passwordEncoder;
//
//    @InjectMocks
//    private UserService userService;
//
//    @InjectMocks
//    private JWTTokenUtils jwtTokenUtils;
//
//    @Value("${jwt.secret}")
//    private String secret;
//
//    @Value("${jwt.expiration}")
//    private long jwtExpirationTime;
//
//    private final String
//            ADMIN_USERNAME = "admin@mail.com",
//            ADMIN_PASSWORD_ENCODED = "$2a$10$ts2FXg1jVvuuEIJqIItTB.Ra1ZklHcSYyrnt3AkGVJkekFUjWcu9K",
//            ADMIN_PASSWORD_DECODED = "1234",
//            USER_USERNAME = "user@mail.com",
//            USER_PASSWORD_ENCODED = "$2a$10$ts2FXg1jVvuuEIJqIItTB.Ra1ZklHcSYyrnt3AkGVJkekFUjWcu9K",
//            USER_PASSWORD_DECODED = "1234",
//            BAD_USERNAME = "baduser@mail.com",
//            BAD_PASSWORD = "12345";
//
//    private final User user, admin;
//
//    public AuthControllerTest() {
//        user = new User(
//                1L,
//                USER_USERNAME,
//                USER_PASSWORD_ENCODED,
//                Collections.singletonList(new Role(0L, "USER")),
//                true,
//                true,
//                true,
//                true,
//                "Ivan",
//                "Ivanov",
//                new Date()
//        );
//
//        admin = new User(
//                2L,
//                ADMIN_USERNAME,
//                ADMIN_PASSWORD_ENCODED,
//                Collections.singletonList(new Role(1L, "ADMIN")),
//                true,
//                true,
//                true,
//                true,
//                "Петр",
//                "Петров",
//                new Date()
//        );
//
//        MockitoAnnotations.openMocks(this);
//
//    }
//
//    @Test
//    @DisplayName("User authorization test")
////    @Disabled
//    public void userAuthorizationTest() throws Exception {
//        when(userRepository.findUserByUsername(USER_USERNAME))
//                .thenReturn(user);
//        when(userRepository.findUserByUsername(ADMIN_USERNAME))
//                .thenReturn(admin);
//
//        when(roleService.getRoleByName("USER"))
//                .thenReturn(new Role("USER"));
//
//        when((passwordEncoder.encode(USER_PASSWORD_DECODED)))
//                .thenReturn(USER_PASSWORD_ENCODED);
//        when((passwordEncoder.encode(ADMIN_PASSWORD_DECODED)))
//                .thenReturn(ADMIN_PASSWORD_ENCODED);
//
//        JwtRequest jwtRequest = new JwtRequest();
//        jwtRequest.setUsername(USER_USERNAME);
//        jwtRequest.setPassword(USER_PASSWORD_DECODED);
//        ObjectMapper mapper = new ObjectMapper();
//        String jwtRequestStr = mapper.writeValueAsString(jwtRequest);
//        mockMvc
//                .perform(post("/auth")
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .content(jwtRequestStr)
//                )
//                .andExpect(status().isOk())
//                .andDo(print());
//    }
//}

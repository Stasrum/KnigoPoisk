package com.geekbrains.knigopoisk.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geekbrains.knigopoisk.dto.JwtRequest;
import com.geekbrains.knigopoisk.dto.JwtResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("User authentication test is success")
    void successUserAuthenticationTest() throws Exception {

        JwtRequest jwtRequest = new JwtRequest();
        jwtRequest.setUsername("admin");
        jwtRequest.setPassword("123");
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

    @Test
    @DisplayName("User authentication test is fail")
    void failUserAuthenticationTest() throws Exception {

        JwtRequest jwtRequest = new JwtRequest();
        jwtRequest.setUsername("admin");
        jwtRequest.setPassword("1234");
        ObjectMapper mapper = new ObjectMapper();
        String jwtRequestStr = mapper.writeValueAsString(jwtRequest);

        mockMvc
                .perform(post("/auth")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(jwtRequestStr)
                )
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    @DisplayName("User authorization test is success")
    void successUserAuthorizationTest() throws Exception{
        String token = getJwtToken();
        mockMvc
                .perform(
                        get("/api/v1/user/profile")
                        .header("Authorization", "Bearer " + token)
                )
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("User authorization test is fail (Forbidden)")
    void failUserAuthorizationTest() throws Exception{
        String token = getJwtToken();
        mockMvc
                .perform(
                        get("/api/v1/user/profile")
                )
                .andExpect(status().isForbidden());
    }

    private String getJwtToken() throws Exception{
        JwtRequest jwtRequest = new JwtRequest();
        jwtRequest.setUsername("admin");
        jwtRequest.setPassword("123");
        ObjectMapper mapper = new ObjectMapper();
        String jwtRequestStr = mapper.writeValueAsString(jwtRequest);
        final String[] token = new String[1];

        mockMvc
                .perform(post("/auth")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(jwtRequestStr)
                )
                .andExpect(status().isOk())
                .andDo(result -> {
                    String jwtResponseStr = result.getResponse().getContentAsString();
                    JwtResponse jwtResponse = mapper.readValue(jwtResponseStr, JwtResponse.class);
                    token[0] = jwtResponse.getToken();
                });
        return token[0];
    }
}

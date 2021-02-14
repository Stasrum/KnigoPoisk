package com.geekbrains.knigopoisk.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geekbrains.knigopoisk.dto.UserRegistrationDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    private final ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void userRegistrationIntegrationTest() throws Exception {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        userRegistrationDto.setUserName("testUser");
        userRegistrationDto.setFirstName("testUserFirstName");
        userRegistrationDto.setLastName("testUserLastName");
        userRegistrationDto.setEmail("email@mail.ru");
        userRegistrationDto.setPassword("Qwerty123!");
        userRegistrationDto.setMatchingPassword("Qwerty123!");
        userRegistrationDto.setBirthDay("2030-01-01");

        mockMvc.perform(post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(userRegistrationDto)))
                .andExpect(status().isCreated());
    }

}

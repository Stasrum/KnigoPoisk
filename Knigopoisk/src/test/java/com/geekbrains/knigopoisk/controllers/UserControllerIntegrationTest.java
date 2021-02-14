package com.geekbrains.knigopoisk.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geekbrains.knigopoisk.dto.UserDetailsDto;
import com.geekbrains.knigopoisk.dto.UserPasswordDto;
import com.geekbrains.knigopoisk.dto.UserRegistrationDto;
import com.geekbrains.knigopoisk.entities.User;
import com.geekbrains.knigopoisk.services.contracts.UserService;
import com.geekbrains.knigopoisk.testUtils.TestDtoModels;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {
    private final ObjectMapper mapper = new ObjectMapper();
    private final TestDtoModels models = new TestDtoModels();
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserService userService;

    @Test
    public void successfulUserRegistrationIntegrationTest() throws Exception {
        UserRegistrationDto userRegistrationDto = models.getUserRegistrationDto();

        mockMvc.perform(post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .accept("application/json;charset=UTF-8")
                .content(mapper.writeValueAsString(userRegistrationDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userName").value(userRegistrationDto.getUserName()))
                .andExpect(jsonPath("$.firstName").value(userRegistrationDto.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(userRegistrationDto.getLastName()))
                .andExpect(jsonPath("$.email").value(userRegistrationDto.getEmail()))
                .andExpect(jsonPath("$.birthDay").value(userRegistrationDto.getBirthDay()));

        User user = userService.findByUserName(userRegistrationDto.getUserName());
        Assert.assertTrue(passwordEncoder.matches(userRegistrationDto.getPassword(), user.getPassword()));
    }

    @Test
    public void unSuccessfulUserRegistrationIntegrationTest() throws Exception {
        UserRegistrationDto userRegistrationDto = models.getUserRegistrationDto();
        userRegistrationDto.setUserName("testUser3");

        mockMvc.perform(post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .accept("application/json;charset=UTF-8")
                .content(mapper.writeValueAsString(userRegistrationDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Пользователь с таким именем уже существует"));
    }

    @Test
    public void userUpdateIntegrationTest() throws Exception {
        User user = userService.findByUserId(1L);
        UserDetailsDto userDetailsDto = models.getUserDetailsDto();

        mockMvc.perform(post("/users/update")
                .contentType(MediaType.APPLICATION_JSON)
                .accept("application/json;charset=UTF-8")
                .content(mapper.writeValueAsString(userDetailsDto)))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.userName").value(user.getUsername()))
                .andExpect(jsonPath("$.firstName").value(userDetailsDto.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(userDetailsDto.getLastName()))
                .andExpect(jsonPath("$.email").value(userDetailsDto.getEmail()))
                .andExpect(jsonPath("$.birthDay").value(userDetailsDto.getBirthDay()));

        User updatedUser = userService.findByUserId(1L);
        Assert.assertTrue(passwordEncoder.matches("123", updatedUser.getPassword()));
    }

    @Test
    public void userPasswordChangeIntegrationTest() throws Exception {
        UserPasswordDto userPasswordDto = models.getUserPasswordDto();

        mockMvc.perform(post("/users/2/changePassword")
                .contentType(MediaType.APPLICATION_JSON)
                .accept("application/json;charset=UTF-8")
                .content(mapper.writeValueAsString(userPasswordDto)))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.message").value("Пароль успешно изменён"));

        User updatedUser = userService.findByUserId(2L);
        Assert.assertTrue(passwordEncoder.matches("Qwerty123!", updatedUser.getPassword()));
    }

    @Test
    public void userAddRoleIntegrationTest() throws Exception {
        mockMvc.perform(post("/users/1/addRole")
                .contentType(MediaType.APPLICATION_JSON)
                .accept("application/json;charset=UTF-8")
                .content(mapper.writeValueAsString(models.getRoleDto("ADMIN"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*]").isArray())
                .andExpect(jsonPath("$.[?(@.name=='ADMIN')]").exists());
    }

    @Test
    public void userRemoveRoleIntegrationTest() throws Exception {
        mockMvc.perform(post("/users/2/removeRole")
                .contentType(MediaType.APPLICATION_JSON)
                .accept("application/json;charset=UTF-8")
                .content(mapper.writeValueAsString(models.getRoleDto("ADMIN"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*]").isArray())
                .andExpect(jsonPath("$.[?(@.name=='ADMIN')]").doesNotExist());
    }
}

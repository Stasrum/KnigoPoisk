package com.geekbrains.knigopoisk.controllers;

import com.geekbrains.knigopoisk.dto.UserRegistrationDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class UserControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void userRegistrationIntegrationTest() throws Exception {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        userRegistrationDto.setUserName("testUser");
        userRegistrationDto.setFirstName("testUserFirstName");
        userRegistrationDto.setLastName("testUserLastName");
        userRegistrationDto.setEmail("email@mail.ru");
        userRegistrationDto.setPassword("Qwerty123!");
        userRegistrationDto.setMatchingPassword("Qwerty123!");
        userRegistrationDto.setBirthDay("2000-01-01");



        this.webTestClient.post().uri("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(userRegistrationDto), UserRegistrationDto.class)
                .exchange()
                .expectStatus().isCreated();
    }

}

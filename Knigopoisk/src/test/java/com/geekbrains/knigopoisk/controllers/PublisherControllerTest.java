package com.geekbrains.knigopoisk.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geekbrains.knigopoisk.dto.PublisherDto;
import com.geekbrains.knigopoisk.entities.Publisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class PublisherControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private PublisherDto publisherDto;

    @BeforeEach
    void setUp() {
        Publisher publisher = new Publisher("Наука", null);
        publisherDto = new PublisherDto(publisher);
    }

    @Test
    void getAllPublishers() throws Exception {
        mockMvc.perform(get("/api/v1/publishers"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.[0].name").value("Williams"))
                .andExpect(jsonPath("$.[1].name").value("Азбука — Аттикус"))
                .andExpect(jsonPath("$.[2].name").value("Бином. Лаборатория знаний"))
                .andExpect(jsonPath("$.[3].name").value("ОЛМА Медиа Групп/ИД Просвещение"))
                .andExpect(jsonPath("$.[4].name").value("Просвещение"))
                .andExpect(jsonPath("$.[5].name").value("Росмэн"))
                .andExpect(jsonPath("$.[6].name").value("Экзамен"))
                .andExpect(jsonPath("$.[7].name").value("Эксмо — АСТ"));
    }

    @Test
    void createPublisher() throws Exception {
        String content = mockMvc.perform(post("/api/v1/publisher/create")
                .content(objectMapper.writeValueAsString(publisherDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(content);
    }

    @Test
    void updatePublisher() throws Exception {
        String content = mockMvc.perform(put("/api/v1/publisher/update")
                .content(objectMapper.writeValueAsString(publisherDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(content);
    }

    @Test
    void deletePublisherById() throws Exception {
        mockMvc.perform(delete("/api/v1/publisher/{id}", 9))
                .andExpect(status().isOk());
    }
}

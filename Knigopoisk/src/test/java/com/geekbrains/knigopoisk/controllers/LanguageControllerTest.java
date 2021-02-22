package com.geekbrains.knigopoisk.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geekbrains.knigopoisk.dto.LanguageDto;
import com.geekbrains.knigopoisk.entities.Book;
import com.geekbrains.knigopoisk.entities.Language;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collection;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class LanguageControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private Collection<Book> books;
    private LanguageDto languageDto;

    @BeforeEach
    void setUp() {
        books = new ArrayList<>();
        Language language = new Language("Испанский", books);
        languageDto = new LanguageDto(language);
    }

    @Test
    void getAllLanguages() throws Exception {
        mockMvc.perform(get("/api/v1/languages"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.[0].name").value("Английский"))
                .andExpect(jsonPath("$.[1].name").value("Немецкий"))
                .andExpect(jsonPath("$.[2].name").value("Русский"))
                .andExpect(jsonPath("$.[3].name").value("Французский"));
    }

    @Test
    void createLanguage() throws Exception {
        String content = mockMvc.perform(post("/api/v1/language/create")
                .content(objectMapper.writeValueAsString(languageDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(content);
    }

    @Test
    void updateLanguage() throws Exception {
        String content = mockMvc.perform(put("/api/v1/language/update")
                .content(objectMapper.writeValueAsString(languageDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(content);
    }

    @Test
    void deleteLanguageById() throws Exception {
        mockMvc.perform(delete("/api/v1/language/{id}", 5))
                .andExpect(status().isOk());
    }
}
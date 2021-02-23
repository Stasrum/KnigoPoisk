package com.geekbrains.knigopoisk.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geekbrains.knigopoisk.dto.GenreDto;
import com.geekbrains.knigopoisk.entities.Book;
import com.geekbrains.knigopoisk.entities.Genre;
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
class GenreControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private Collection<Book> books;
    private GenreDto genreDto;

    @BeforeEach
    void setUp() {
        books = new ArrayList<>();
        Genre genre = new Genre("Биография", null, books);
        genreDto = new GenreDto(genre);
    }

    @Test
    void getAllGenres() throws Exception {
        mockMvc.perform(get("/api/v1/genres"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.[0].name").value("Детектив"))
                .andExpect(jsonPath("$.[1].name").value("Приключения"))
                .andExpect(jsonPath("$.[2].name").value("Фантастика"));
    }

    @Test
    void createGenre() throws Exception {
        String content = mockMvc.perform(post("/api/v1/genre/create")
                .content(objectMapper.writeValueAsString(genreDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(content);
    }

    @Test
    void updateGenre() throws Exception {
        String content = mockMvc.perform(put("/api/v1/genre/update")
                .content(objectMapper.writeValueAsString(genreDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(content);
    }

    @Test
    void deleteGenreById() throws Exception {
        mockMvc.perform(delete("/api/v1/genre/{id}", 4))
                .andExpect(status().isOk());
    }
}
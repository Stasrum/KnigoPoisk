package com.geekbrains.knigopoisk.services.impl;

import com.geekbrains.knigopoisk.dto.AuthorDto;
import com.geekbrains.knigopoisk.entities.Author;
import com.geekbrains.knigopoisk.repositories.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {

    @InjectMocks
    private AuthorServiceImpl authorService;

    @Mock
    private AuthorRepository authorRepository;

    private OffsetDateTime created;
    private OffsetDateTime updated;
    private Author author1;
    private Author author2;

    @BeforeEach
    void setUp() {
        created = OffsetDateTime.of(2000, 1, 1, 1, 1, 1, 1, ZoneOffset.UTC);
        updated = OffsetDateTime.of(2000, 1, 1, 1, 2, 1, 1, ZoneOffset.UTC);

        author1 = new Author("Джошуа Блох", "java author");
        author1.setId(1L);
        author1.setCreated(created);
        author1.setUpdated(updated);

        author2 = new Author("Герберт Шилдт", "java author");
        author2.setId(2L);
        author2.setCreated(created);
        author2.setUpdated(updated);
    }


    @Test
    void getAll() {
        List<Author> authors = Arrays.asList(author1, author2);

        when(authorRepository.findAllByOrderByNameAsc()).thenReturn(authors);

        List<AuthorDto> expectedAuthors = authorService.getAll();
        List<AuthorDto> actualAuthors = authors.stream().map(AuthorDto::new).collect(Collectors.toList());

        assertNotNull(expectedAuthors);
        assertEquals(expectedAuthors, actualAuthors);
    }

    @Test
    void save() {
        when(authorRepository.save(any())).thenReturn(author1);

        AuthorDto expectedAuthor = authorService.save(new AuthorDto());
        AuthorDto actualAuthor = new AuthorDto(author1);

        assertEquals(expectedAuthor, actualAuthor);
    }

    @Test
    void update() {
        when(authorRepository.findOneByName(any())).thenReturn(Optional.of(author1));
        when(authorRepository.save(any())).thenReturn(author1);

        AuthorDto expectedAuthor = authorService.update(new AuthorDto());
        AuthorDto actualAuthor = new AuthorDto(author1);

        assertEquals(expectedAuthor, actualAuthor);
    }
}
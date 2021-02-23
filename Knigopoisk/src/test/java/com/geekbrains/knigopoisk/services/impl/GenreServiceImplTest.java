package com.geekbrains.knigopoisk.services.impl;

import com.geekbrains.knigopoisk.dto.GenreDto;
import com.geekbrains.knigopoisk.entities.Book;
import com.geekbrains.knigopoisk.entities.Genre;
import com.geekbrains.knigopoisk.repositories.GenreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GenreServiceImplTest {

    @InjectMocks
    private GenreServiceImpl genreService;

    @Mock
    private GenreRepository genreRepository;

    private OffsetDateTime created;
    private OffsetDateTime updated;
    private Collection<Book> books;
    private Genre genre1;
    private Genre genre2;

    @BeforeEach
    void setUp() {
        books = new ArrayList<>();

        created = OffsetDateTime.of(2000, 1, 1, 1, 1, 1, 1, ZoneOffset.UTC);
        updated = OffsetDateTime.of(2000, 1, 1, 1, 2, 1, 1, ZoneOffset.UTC);

        genre1 = new Genre("Научная статья",
                "Произведение, рассматривающее определенную научную проблему, методы ее решения.", books);
        genre1.setId(1L);
        genre1.setCreated(created);
        genre1.setUpdated(updated);

        genre2 = new Genre("Научно-популярная статья",
                "В отличие от научной статьи, в ней излагаются данные, понятные широкой аудитории.", books);
        genre2.setId(2L);
        genre2.setCreated(created);
        genre2.setUpdated(updated);
    }

    @Test
    void getAll() {
        List<Genre> genres = Arrays.asList(genre1, genre2);

        when(genreRepository.findAllByOrderByNameAsc()).thenReturn(genres);

        List<GenreDto> expectedGenres = genreService.getAll();
        List<GenreDto> actualGenres = genres.stream().map(GenreDto::new).collect(Collectors.toList());

        assertNotNull(expectedGenres);
        assertEquals(expectedGenres, actualGenres);
    }

    @Test
    void save() {
        when(genreRepository.save(any())).thenReturn(genre1);

        GenreDto expectedGenre = genreService.save(new GenreDto());
        GenreDto actualGenre = new GenreDto(genre1);

        assertEquals(expectedGenre, actualGenre);
    }

    @Test
    void update() {
        when(genreRepository.findOneByName(any())).thenReturn(Optional.of(genre1));
        when(genreRepository.save(any())).thenReturn(genre1);

        GenreDto expectedGenre= genreService.update(new GenreDto());
        GenreDto actualGenre = new GenreDto(genre1);

        assertEquals(expectedGenre, actualGenre);
    }
}
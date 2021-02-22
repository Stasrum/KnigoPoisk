package com.geekbrains.knigopoisk.services.impl;

import com.geekbrains.knigopoisk.dto.LanguageDto;
import com.geekbrains.knigopoisk.entities.Book;
import com.geekbrains.knigopoisk.entities.Language;
import com.geekbrains.knigopoisk.repositories.LanguageRepository;
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
class LanguageServiceImplTest {

    @InjectMocks
    private LanguageServiceImpl languageService;

    @Mock
    private LanguageRepository languageRepository;

    private OffsetDateTime created;
    private OffsetDateTime updated;
    private Collection<Book> books;
    private Language language1;
    private Language language2;

    @BeforeEach
    void setUp() {
        books = new ArrayList<>();

        created = OffsetDateTime.of(2000, 1, 1, 1, 1, 1, 1, ZoneOffset.UTC);
        updated = OffsetDateTime.of(2000, 1, 1, 1, 2, 1, 1, ZoneOffset.UTC);

        language1 = new Language("Английский", books);
        language1.setId(1L);
        language1.setCreated(created);
        language1.setUpdated(updated);

        language2 = new Language("Испанский", books);
        language2.setId(2L);
        language2.setCreated(created);
        language2.setUpdated(updated);

    }

    @Test
    void getAll() {
        List<Language> languages = Arrays.asList(language1, language2);

        when(languageRepository.findAllByOrderByNameAsc()).thenReturn(languages);

        List<LanguageDto> expectedLanguages = languageService.getAll();
        List<LanguageDto> actualLanguages = languages.stream().map(LanguageDto::new).collect(Collectors.toList());

        assertNotNull(expectedLanguages);
        assertEquals(expectedLanguages, actualLanguages);
    }

    @Test
    void save() {
        when(languageRepository.save(any())).thenReturn(language1);

        LanguageDto expectedLanguage = languageService.save(new LanguageDto());
        LanguageDto actualLanguage = new LanguageDto(language1);

        assertEquals(expectedLanguage, actualLanguage);
    }

    @Test
    void update() {
        when(languageRepository.findOneByName(any())).thenReturn(Optional.of(language1));
        when(languageRepository.save(any())).thenReturn(language1);

        LanguageDto expectedLanguage= languageService.update(new LanguageDto());
        LanguageDto actualLanguage = new LanguageDto(language1);

        assertEquals(expectedLanguage, actualLanguage);
    }
}
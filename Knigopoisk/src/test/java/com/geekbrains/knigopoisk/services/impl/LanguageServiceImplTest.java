package com.geekbrains.knigopoisk.services.impl;

import com.geekbrains.knigopoisk.dto.LanguageDto;
import com.geekbrains.knigopoisk.entities.Book;
import com.geekbrains.knigopoisk.entities.Language;
import com.geekbrains.knigopoisk.repositories.LanguageRepository;
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

    @Test
    void getAll() {
        Collection<Book> books = new ArrayList<>();

        OffsetDateTime created = OffsetDateTime.of(2000, 1, 1, 1, 1, 1, 1, ZoneOffset.UTC);
        OffsetDateTime updated = OffsetDateTime.of(2000, 1, 1, 1, 2, 1, 1, ZoneOffset.UTC);

        Language language1 = new Language("Английский", books);
        language1.setId(1L);
        language1.setCreated(created);
        language1.setUpdated(updated);

        Language language2 = new Language("Испанский", books);
        language2.setId(2L);
        language2.setCreated(created);
        language2.setUpdated(updated);

        List<Language> languages = Arrays.asList(language1, language2);

        when(languageRepository.findAllByOrderByNameAsc()).thenReturn(languages);

        List<LanguageDto> expectedLanguages = languageService.getAll();
        List<LanguageDto> actualLanguages = languages.stream().map(LanguageDto::new).collect(Collectors.toList());

        assertNotNull(expectedLanguages);
        assertEquals(expectedLanguages, actualLanguages);
    }

    @Test
    void save() {
        Collection<Book> books = new ArrayList<>();

        OffsetDateTime created = OffsetDateTime.of(2000, 1, 1, 1, 1, 1, 1, ZoneOffset.UTC);
        OffsetDateTime updated = OffsetDateTime.of(2000, 1, 1, 1, 2, 1, 1, ZoneOffset.UTC);

        Language language = new Language("Английский", books);
        language.setId(1L);
        language.setCreated(created);
        language.setUpdated(updated);

        when(languageRepository.save(any())).thenReturn(language);

        LanguageDto expectedLanguage = languageService.save(new LanguageDto());
        LanguageDto actualLanguage = new LanguageDto(language);

        assertEquals(expectedLanguage, actualLanguage);
    }

    @Test
    void update() {
        Collection<Book> books = new ArrayList<>();

        OffsetDateTime created = OffsetDateTime.of(2000, 1, 1, 1, 1, 1, 1, ZoneOffset.UTC);

        Language language = new Language("Английский", books);
        language.setId(1L);
        language.setCreated(created);
        language.setUpdated(OffsetDateTime.now());

        when(languageRepository.findOneByName(any())).thenReturn(Optional.of(language));
        when(languageRepository.save(any())).thenReturn(language);

        LanguageDto expectedLanguage= languageService.update(new LanguageDto());
        LanguageDto actualLanguage = new LanguageDto(language);

        assertEquals(expectedLanguage, actualLanguage);
    }
}
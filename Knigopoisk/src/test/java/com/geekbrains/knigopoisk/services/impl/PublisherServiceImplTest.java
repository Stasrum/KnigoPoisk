package com.geekbrains.knigopoisk.services.impl;

import com.geekbrains.knigopoisk.dto.PublisherDto;
import com.geekbrains.knigopoisk.entities.Book;
import com.geekbrains.knigopoisk.entities.Publisher;
import com.geekbrains.knigopoisk.repositories.PublisherRepository;
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
class PublisherServiceImplTest {

    @InjectMocks
    private PublisherServiceImpl publisherService;

    @Mock
    private PublisherRepository publisherRepository;

    private OffsetDateTime created;
    private OffsetDateTime updated;
    private Publisher publisher1;
    private Publisher publisher2;

    @BeforeEach
    void setUp() {
        created = OffsetDateTime.of(2000, 1, 1, 1, 1, 1, 1, ZoneOffset.UTC);
        updated = OffsetDateTime.of(2000, 1, 1, 1, 2, 1, 1, ZoneOffset.UTC);

        publisher1 = new Publisher("Наука", "Советское и российское академическое издательство книг и журналов.");
        publisher1.setId(1L);
        publisher1.setCreated(created);
        publisher1.setUpdated(updated);

        publisher2 = new Publisher("Дрофа", "Российское специализированное издательство учебной литературы.");
        publisher2.setId(2L);
        publisher2.setCreated(created);
        publisher2.setUpdated(updated);
    }

    @Test
    void getAll() {
        List<Publisher> publishers = Arrays.asList(publisher1, publisher2);

        when(publisherRepository.findAllByOrderByNameAsc()).thenReturn(publishers);

        List<PublisherDto> expectedPublisher = publisherService.getAll();
        List<PublisherDto> actualPublisher = publishers.stream().map(PublisherDto::new).collect(Collectors.toList());

        assertNotNull(expectedPublisher);
        assertEquals(expectedPublisher, actualPublisher);
    }

    @Test
    void save() {
        when(publisherRepository.save(any())).thenReturn(publisher1);

        PublisherDto expectedPublisher = publisherService.save(new PublisherDto());
        PublisherDto actualPublisher = new PublisherDto(publisher1);

        assertEquals(expectedPublisher, actualPublisher);
    }

    @Test
    void update() {
        when(publisherRepository.findOneByName(any())).thenReturn(Optional.of(publisher1));
        when(publisherRepository.save(any())).thenReturn(publisher1);

        PublisherDto expectedPublisher = publisherService.update(new PublisherDto());
        PublisherDto actualPublisher = new PublisherDto(publisher1);

        assertEquals(expectedPublisher, actualPublisher);
    }
}
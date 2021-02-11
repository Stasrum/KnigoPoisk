package com.geekbrains.knigopoisk.services.impl;

import com.geekbrains.knigopoisk.dto.PublisherDto;
import com.geekbrains.knigopoisk.entities.Publisher;
import com.geekbrains.knigopoisk.repositories.PublisherRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PublisherServiceImplTest {

    @InjectMocks
    private PublisherServiceImpl publisherService;

    @Mock
    private PublisherRepository publisherRepository;

    @Test
    void getAll() {
        OffsetDateTime created = OffsetDateTime.of(2000, 1, 1, 1, 1, 1, 1, ZoneOffset.UTC);
        OffsetDateTime updated = OffsetDateTime.of(2000, 1, 1, 1, 2, 1, 1, ZoneOffset.UTC);

        Publisher publisher1 = new Publisher("Наука", "Советское и российское академическое издательство книг и журналов.");
        publisher1.setId(1L);
        publisher1.setCreated(created);
        publisher1.setUpdated(updated);

        Publisher publisher2 = new Publisher("Дрофа", "Российское специализированное издательство учебной литературы.");
        publisher2.setId(2L);
        publisher2.setCreated(created);
        publisher2.setUpdated(updated);

        List<Publisher> publishers = Arrays.asList(publisher1, publisher2);

        when(publisherRepository.findAllByOrderByNameAsc()).thenReturn(publishers);

        List<PublisherDto> expectedPublisher = publisherService.getAll();
        List<PublisherDto> actualPublisher = publishers.stream().map(PublisherDto::new).collect(Collectors.toList());

        assertNotNull(expectedPublisher);
        assertEquals(expectedPublisher, actualPublisher);
    }

    @Test
    void save() {
        OffsetDateTime created = OffsetDateTime.of(2000, 1, 1, 1, 1, 1, 1, ZoneOffset.UTC);
        OffsetDateTime updated = OffsetDateTime.of(2000, 1, 1, 1, 2, 1, 1, ZoneOffset.UTC);

        Publisher publisher = new Publisher("Наука", "Советское и российское академическое издательство книг и журналов.");
        publisher.setId(1L);
        publisher.setCreated(created);
        publisher.setUpdated(updated);

        when(publisherRepository.save(any())).thenReturn(publisher);

        PublisherDto expectedPublisher = publisherService.save(new PublisherDto());
        PublisherDto actualPublisher = new PublisherDto(publisher);

        assertEquals(expectedPublisher, actualPublisher);
    }

    @Test
    void update() {
        OffsetDateTime created = OffsetDateTime.of(2000, 1, 1, 1, 1, 1, 1, ZoneOffset.UTC);

        Publisher publisher = new Publisher("Наука", "Советское и российское академическое издательство книг и журналов.");
        publisher.setId(1L);
        publisher.setCreated(created);
        publisher.setUpdated(OffsetDateTime.now());

        when(publisherRepository.findOneByName(any())).thenReturn(Optional.of(publisher));
        when(publisherRepository.save(any())).thenReturn(publisher);

        PublisherDto expectedPublisher = publisherService.update(new PublisherDto());
        PublisherDto actualPublisher = new PublisherDto(publisher);

        assertEquals(expectedPublisher, actualPublisher);
    }
}
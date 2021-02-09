package com.geekbrains.knigopoisk.services.impl;

import com.geekbrains.knigopoisk.dto.BookDto;
import com.geekbrains.knigopoisk.entities.*;
import com.geekbrains.knigopoisk.repositories.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static com.geekbrains.knigopoisk.testUtils.Books.*;
import static org.mockito.Mockito.*;

class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private LanguageRepository languageRepository;

    @Mock
    private GenreRepository genreRepository;

    @Mock
    private PublisherRepository publisherRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    public BookServiceImplTest() {
        MockitoAnnotations.openMocks(this);

        Book book = getBook();
        when(bookRepository.save(any()))
                .thenReturn(book);
        when(bookRepository.findById(BOOK_ID))
                .thenReturn(Optional.of(book));
        when((authorRepository.findOneByName(AUTHOR_1)))
                .thenReturn(Optional.of(new Author(AUTHOR_1, "")));
        when((authorRepository.findOneByName(AUTHOR_2)))
                .thenReturn(Optional.of(new Author(AUTHOR_2, "")));
        when((languageRepository.findOneByName(LANGUAGE_1)))
                .thenReturn(Optional.of(new Language(LANGUAGE_1, Collections.singletonList(book))));
        when((languageRepository.findOneByName(LANGUAGE_2)))
                .thenReturn(Optional.of(new Language(LANGUAGE_2, Collections.singletonList(book))));
        when((genreRepository.findOneByName(GENRE_1)))
                .thenReturn(Optional.of(new Genre(GENRE_1, "", Collections.singletonList(book))));
        when((publisherRepository.findOneByName(PUBLISHER_1)))
                .thenReturn(Optional.of(new Publisher(PUBLISHER_1, "")));
    }

    @Test
    @DisplayName("Adding book is success")
    void addSuccessTest() {
        Book book = getBook();
        BookDto bDto = new BookDto(book);
        BookDto bookDto = bookService.add(bDto);
        Assertions.assertEquals(bookDto.getAuthors().get(1).getName(), AUTHORS.get(1).getName());
        Assertions.assertEquals(bookDto.getGenres().get(0).getName(), GENRES.get(0).getName());
        Assertions.assertEquals(bookDto.getDescription(), DESCRIPTION);
        Assertions.assertEquals(bookDto.getTitle(), TITLE);
    }

    @Test
    @DisplayName("Edit book is success")
    void editSuccessTest() {
        BookDto bookDto = bookService.edit(new BookDto(getBook()));
        Assertions.assertEquals(bookDto.getAuthors().get(1).getName(), AUTHORS.get(1).getName());
        Assertions.assertEquals(bookDto.getGenres().get(0).getName(), GENRES.get(0).getName());
        Assertions.assertEquals(bookDto.getDescription(), DESCRIPTION);
        Assertions.assertEquals(bookDto.getTitle(), TITLE);
    }
}
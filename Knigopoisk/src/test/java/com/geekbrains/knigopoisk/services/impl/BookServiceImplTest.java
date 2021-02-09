package com.geekbrains.knigopoisk.services.impl;

import com.geekbrains.knigopoisk.dto.BookDto;
import com.geekbrains.knigopoisk.entities.*;
import com.geekbrains.knigopoisk.exceptions.AuthorNotFoundException;
import com.geekbrains.knigopoisk.exceptions.GenreNotFoundException;
import com.geekbrains.knigopoisk.exceptions.LanguageNotFoundException;
import com.geekbrains.knigopoisk.exceptions.PublisherNotFoundException;
import com.geekbrains.knigopoisk.repositories.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static com.geekbrains.knigopoisk.testUtils.Books.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

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
        BookDto bookDto = bookService.add(new BookDto(getBook()));

        Assertions.assertEquals(bookDto.getAuthors().get(1).getName(), AUTHORS.get(1).getName());
        Assertions.assertEquals(bookDto.getGenres().get(0).getName(), GENRES.get(0).getName());
        Assertions.assertEquals(bookDto.getDescription(), DESCRIPTION);
        Assertions.assertEquals(bookDto.getTitle(), TITLE);
    }

    @Test
    @DisplayName("Adding book is fail. Author not found exception")
    void addAuthorNotFoundExceptionTest() {
        Book book = getBook();
        book.setAuthors(Collections.singletonList(new Author("notFoundAuthor", "")));

        Assertions.assertThrows(AuthorNotFoundException.class, ()->bookService.add(new BookDto(book)));
    }

    @Test
    @DisplayName("Adding book is fail. Genre not found exception")
    void addGenreNotFoundExceptionTest() {
        Book book = getBook();
        book.setGenres(Collections.singletonList(new Genre("notFoundGenre", "", Collections.emptyList())));

        Assertions.assertThrows(GenreNotFoundException.class, ()->bookService.add(new BookDto(book)));
    }

    @Test
    @DisplayName("Adding book is fail. Language not found exception")
    void addLanguageNotFoundExceptionTest() {
        Book book = getBook();
        book.setLanguages(Collections.singletonList(new Language("notFoundLanguage", Collections.emptyList())));

        Assertions.assertThrows(LanguageNotFoundException.class, ()->bookService.add(new BookDto(book)));
    }

    @Test
    @DisplayName("Adding book is fail. Publisher not found exception")
    void addPublisherNotFoundExceptionTest() {
        Book book = getBook();
        book.setPublisher(new Publisher("notFoundPublisher", ""));

        Assertions.assertThrows(PublisherNotFoundException.class, ()->bookService.add(new BookDto(book)));
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

    @Test
    @DisplayName("Editing book is fail. Author not found exception")
    void editAuthorNotFoundExceptionTest() {
        Book book = getBook();
        book.setAuthors(Collections.singletonList(new Author("notFoundAuthor", "")));

        Assertions.assertThrows(AuthorNotFoundException.class, ()->bookService.edit(new BookDto(book)));
    }

    @Test
    @DisplayName("Editing book is fail. Genre not found exception")
    void editGenreNotFoundExceptionTest() {
        Book book = getBook();
        book.setGenres(Collections.singletonList(new Genre("notFoundGenre", "", Collections.emptyList())));

        Assertions.assertThrows(GenreNotFoundException.class, ()->bookService.edit(new BookDto(book)));
    }

    @Test
    @DisplayName("Editing book is fail. Language not found exception")
    void editLanguageNotFoundExceptionTest() {
        Book book = getBook();
        book.setLanguages(Collections.singletonList(new Language("notFoundGenre", Collections.emptyList())));

        Assertions.assertThrows(LanguageNotFoundException.class, ()->bookService.edit(new BookDto(book)));
    }

    @Test
    @DisplayName("Editing book is fail. Publisher not found exception")
    void editPublisherNotFoundExceptionTest() {
        Book book = getBook();
        book.setPublisher(new Publisher("notFoundGenre", ""));

        Assertions.assertThrows(PublisherNotFoundException.class, ()->bookService.edit(new BookDto(book)));
    }
}
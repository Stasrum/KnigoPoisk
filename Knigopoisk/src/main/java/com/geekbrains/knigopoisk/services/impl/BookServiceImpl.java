package com.geekbrains.knigopoisk.services.impl;

import com.geekbrains.knigopoisk.dto.AuthorDto;
import com.geekbrains.knigopoisk.dto.BookDto;
import com.geekbrains.knigopoisk.dto.GenreDto;
import com.geekbrains.knigopoisk.dto.LanguageDto;
import com.geekbrains.knigopoisk.entities.*;
import com.geekbrains.knigopoisk.exceptions.*;
import com.geekbrains.knigopoisk.repositories.*;
import com.geekbrains.knigopoisk.services.contracts.BookImageService;
import com.geekbrains.knigopoisk.services.contracts.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final LanguageRepository languageRepository;
    private final GenreRepository genreRepository;
    private final PublisherRepository publisherRepository;
    private final BookImageService bookImageService;

    @Override
    public List<BookDto> getAll() {
        List<Book> books = bookRepository.findAllByOrderByTitleAsc();
        return books.stream().map(BookDto::new).collect(Collectors.toList());
    }

    @Override
    public BookDto save(BookDto bookDto) {
        Book b = BookDto.fromDto(bookDto);
        b.setId(null);
        Book book = bookRepository.save(b);
        return new BookDto(book);
    }

    @Override
    public BookDto add(BookDto bookDto) {
        Book book = new Book();
        book.setImages(Collections.EMPTY_LIST);
        getBookFromBookDto(bookDto, book);
        book.setCreated(OffsetDateTime.now());
        Book b = bookRepository.save(book);
        return new BookDto(b);
    }

    @Override
    public BookDto edit(BookDto bookDto) {
        Book book = bookRepository.findById(bookDto.getId()).orElseThrow(()->new BookNotFoundException("Book isn't found"));
        getBookFromBookDto(bookDto, book);
        Book b = bookRepository.save(book);
        return new BookDto(b);
    }

    private void getBookFromBookDto(BookDto bookDto, Book book) {
        book.setTitle(bookDto.getTitle());
        List<Author> authors = new ArrayList<>();
        for (AuthorDto author : bookDto.getAuthors()) {
            Author a = authorRepository.findOneByName(author.getName()).orElseThrow(() -> new AuthorNotFoundException("Author isn't found"));
            authors.add(a);
        }
        book.setAuthors(authors);

        book.setYear(bookDto.getYear());

        book.setIsbn(bookDto.getIsbn());

        List<Language> languages = new ArrayList<>();
        for (LanguageDto language : bookDto.getLanguages()) {
            Language l = languageRepository.findOneByName(language.getName()).orElseThrow(() -> new LanguageNotFoundException("Language isn't found"));
            languages.add(l);
        }
        book.setLanguages(languages);

        List<Genre> genres = new ArrayList<>();
        for (GenreDto genre : bookDto.getGenres()) {
            Genre g = genreRepository.findOneByName(genre.getName()).orElseThrow(() -> new GenreNotFoundException("Genre isn't found"));
            genres.add(g);
        }
        book.setGenres(genres);

        Publisher publisher = publisherRepository.findOneByName(bookDto.getPublisher().getName()).orElseThrow(() -> new PublisherNotFoundException("Publisher isn't found"));
        book.setPublisher(publisher);

        book.setDescription(bookDto.getDescription());

        book.setUpdated(OffsetDateTime.now());
    }

    @Override
    public boolean deleteById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book with id=" + id + " isn't found"));
        bookRepository.delete(book);
        return true;
    }

    @Override
    public BookDto findById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(()->new BookNotFoundException("Book with id=" + id + " isn't found"));
        return new BookDto(book);
    }

    @Override
    public Book findBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(()->new BookNotFoundException("Book with id=" + id + " isn't found"));
        return book;
    }

    @Override
    public Page<Book> findAll(Specification<Book> spec, int page, int size) {
        return bookRepository.findAll(spec, PageRequest.of(page, size, Sort.by("title")));
    }

    @Override
    public BookDto update(BookDto bookDto) {
        Book b = bookRepository.findOneByTitle(bookDto.getTitle()).orElseThrow(()-> new BookNotFoundException("Book isn't found"));
        b.setUpdated(OffsetDateTime.now());
        Book book = bookRepository.save(b);
        return new BookDto(book);
    }
}

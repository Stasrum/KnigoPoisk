package com.geekbrains.knigopoisk.services.impl;

import com.geekbrains.knigopoisk.dto.BookDto;
import com.geekbrains.knigopoisk.entities.*;
import com.geekbrains.knigopoisk.exceptions.*;
import com.geekbrains.knigopoisk.repositories.*;
import com.geekbrains.knigopoisk.services.contracts.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final LanguageRepository languageRepository;
    private final GenreRepository genreRepository;
    private final PublisherRepository publisherRepository;


    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book save(Book book) {
        book.setId(null);
        return bookRepository.save(book);
    }

    @Override
    public Book add(BookDto bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        List<Author> authors = new ArrayList<>();
        for (Author author: bookDto.getAuthors()){
            Author a = authorRepository.findOneByName(author.getName()).orElseThrow(()->new AuthorNotFoundException("Author isn't found"));
            authors.add(a);
        }
        book.setAuthors(authors);

        book.setYear(bookDto.getYear());

        book.setIsbn(bookDto.getIsbn());

        List<Language> languages = new ArrayList<>();
        for (Language language: bookDto.getLanguages()){
            Language l = languageRepository.findOneByName(language.getName()).orElseThrow(()->new LanguageNotFoundException("Language isn't found"));
            languages.add(l);
        }
        book.setLanguages(languages);

        List<Genre> genres = new ArrayList<>();
        for (Genre genre: bookDto.getGenres()){
            Genre g = genreRepository.findOneByName(genre.getName()).orElseThrow(()->new GenreNotFoundException("Genre isn't found"));
            genres.add(g);
        }
        book.setGenres(genres);

        List<Publisher> publishers = new ArrayList<>();
        for (Publisher publisher: bookDto.getPublishers()){
            Publisher p = publisherRepository.findOneByName(publisher.getName()).orElseThrow(()->new PublisherNotFoundException("Publisher isn't found"));
            publishers.add(p);
        }
        book.setPublishers(publishers);

        book.setDescription(bookDto.getDescription());

        book.setUpdated(OffsetDateTime.now());

        book.setCreated(OffsetDateTime.now());

        return bookRepository.save(book);
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (!book.isPresent()) {
            throw new BookNotFoundException("Книга с id = " + id + " не существует");
        }
        bookRepository.delete(book.get());
        //bookRepository.deleteById(id);
        return true;
    }

    @Override
    public Optional<Book> findById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book;
    }

    @Override
    public Page<Book> findAll(Specification<Book> spec, int page, int size) {
        return bookRepository.findAll(spec, PageRequest.of(page, size));
    }

    @Override
    public Book update(Book book) {
        book.setUpdated(OffsetDateTime.now());
        return bookRepository.save(book);
    }
}

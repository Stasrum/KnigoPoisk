package com.geekbrains.knigopoisk.services.impl;

import com.geekbrains.knigopoisk.dto.BookDto;
import com.geekbrains.knigopoisk.entities.Book;
import com.geekbrains.knigopoisk.exceptions.BookNotFoundException;
import com.geekbrains.knigopoisk.repositories.BookRepository;
import com.geekbrains.knigopoisk.services.contracts.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public List<Book> getAll(){
        return bookRepository.findAll();
    }

    @Override
    public Book save(Book book){
        book.setId(null);
        return bookRepository.save(book);
    }

    @Override
    public BookDto save(BookDto bookDto) {
        Book book = new Book();
        book.setId(bookDto.getId());
        book.setTitle(book.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setYear(bookDto.getYear());
        book.setIsbn(book.getIsbn());
        book.setLang(bookDto.getLang());
        book.setGenre(book.getGenre());
        book.setPublisher(book.getPublisher());
        book.setDescription(bookDto.getDescription());
        book.setUpdated(OffsetDateTime.now());
        if (bookDto.getId()==null){
            book.setCreated(OffsetDateTime.now());
        }
        Book newBook = bookRepository.save(book);
        bookDto.setId(newBook.getId());
        return bookDto;
    }

    @Override
    public boolean deleteById(Long id){
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
        Optional<Book> book =  bookRepository.findById(id);
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

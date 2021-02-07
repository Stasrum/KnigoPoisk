package com.geekbrains.knigopoisk.services.contracts;

import com.geekbrains.knigopoisk.dto.BookDto;
import com.geekbrains.knigopoisk.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> getAll();

    Book save(Book book);

    Book save(BookDto bookDto);

    boolean deleteById(Long id);

    Optional<Book> findById(Long id);

    Page<Book> findAll(Specification<Book> spec, int page, int size);

    Book update(Book book);
}

package com.geekbrains.knigopoisk.services.contracts;

import com.geekbrains.knigopoisk.entities.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> getAll();

    Book save(Book book);

    boolean deleteById(Long id);

    Optional<Book> findById(Long id);
}

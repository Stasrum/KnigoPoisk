package com.geekbrains.knigopoisk.services.contracts;

import com.geekbrains.knigopoisk.entities.Book;

import java.util.List;

public interface BookService {

    List<Book> getAll();

    Book save(Book book);

    boolean deleteById(Long id);
}

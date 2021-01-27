package com.geekbrains.knigopoisk.services.contracts;

import com.geekbrains.knigopoisk.entities.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAll();

     Author save(Author author);
}

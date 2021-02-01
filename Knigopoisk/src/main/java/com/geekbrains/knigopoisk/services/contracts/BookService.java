package com.geekbrains.knigopoisk.services.contracts;

import com.geekbrains.knigopoisk.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface BookService {

    List<Book> getAll();

    Book save(Book book);

    boolean deleteById(Long id);

    Page<Book> findAll(Specification<Book> spec, int page, int size);
}

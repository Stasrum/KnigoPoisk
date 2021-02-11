package com.geekbrains.knigopoisk.services.contracts;

import com.geekbrains.knigopoisk.dto.BookDto;
import com.geekbrains.knigopoisk.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface BookService {

    List<BookDto> getAll();

    BookDto save(BookDto book);

    BookDto add(BookDto bookDto);

    BookDto edit(BookDto bookDto);

    boolean deleteById(Long id);

    BookDto findById(Long id);

    Page<Book> findAll(Specification<Book> spec, int page, int size);

    BookDto update(BookDto book);
}

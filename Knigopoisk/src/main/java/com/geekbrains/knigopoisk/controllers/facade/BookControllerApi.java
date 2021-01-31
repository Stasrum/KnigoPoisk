package com.geekbrains.knigopoisk.controllers.facade;

import com.geekbrains.knigopoisk.entities.Book;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface BookControllerApi {

    @GetMapping(value = "/books", produces = "application/json")
    List<Book> getAllBooks();

    @GetMapping(value = "/books/delete/{id}")
    boolean deleteById(@PathVariable("id") Long id);

    @PostMapping(value = "/books/add", consumes = "application/json", produces = "application/json")
    Book createBook(@RequestBody Book book);
}

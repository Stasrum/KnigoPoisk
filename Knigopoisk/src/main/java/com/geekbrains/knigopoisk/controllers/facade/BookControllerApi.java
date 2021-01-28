package com.geekbrains.knigopoisk.controllers.facade;

import com.geekbrains.knigopoisk.entities.Book;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public interface BookControllerApi {

    @GetMapping(value = "/books", produces = "application/json")
    List<Book> getAllBooks();

    @DeleteMapping("/books/{id}")
    void deleteBookById(@PathVariable("id") Long id);

    @PostMapping(path = "/books/add", consumes = "application/json", produces = "application/json")
    Book createBook(@RequestBody Book book);
}

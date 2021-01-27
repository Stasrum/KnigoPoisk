package com.geekbrains.knigopoisk.controllers;

import com.geekbrains.knigopoisk.entities.Book;
import com.geekbrains.knigopoisk.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping(value = "/books", produces = "application/json")
    public List<Book> getAllBooks(){
        return bookService.getAll();
    }

    @DeleteMapping("/books/{id}")
    public void deleteBookById(@PathVariable("id") Long id){
        bookService.deleteById(id);
    }

    @PostMapping(path = "/books/add", consumes = "application/json", produces = "application/json")
    public Book createBook(@RequestBody Book book) {
        book.setId(null);
        return bookService.save(book);
    }
}

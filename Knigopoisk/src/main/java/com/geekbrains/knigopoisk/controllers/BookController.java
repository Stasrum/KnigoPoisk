package com.geekbrains.knigopoisk.controllers;

import com.geekbrains.knigopoisk.entities.Book;
import com.geekbrains.knigopoisk.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping()
public class BookController {
    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("/allbooks")
    public List<Book> getAllBook(){
        return bookService.getAllBooks();
    }

    @GetMapping("/delbook/{id}")
    public void deleteBookById(@PathVariable("id") Long id){
        bookService.deleteBook(id);
    }
}

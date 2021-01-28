package com.geekbrains.knigopoisk.controllers;

import com.geekbrains.knigopoisk.controllers.facade.BookControllerApi;
import com.geekbrains.knigopoisk.entities.Book;
import com.geekbrains.knigopoisk.services.contracts.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
public class BookController implements BookControllerApi {
    private final BookService bookService;

    @Override
    public List<Book> getAllBooks(){
        return bookService.getAll();
    }

    @Override
    public void deleteBookById(@PathVariable("id") Long id){
        bookService.deleteById(id);
    }

    @Override
    public Book createBook(@RequestBody Book book) {
        book.setId(null);
        return bookService.save(book);
    }
}

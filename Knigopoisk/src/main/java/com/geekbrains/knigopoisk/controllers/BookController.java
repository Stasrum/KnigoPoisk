package com.geekbrains.knigopoisk.controllers;

import com.geekbrains.knigopoisk.controllers.facade.BookControllerApi;
import com.geekbrains.knigopoisk.entities.Book;
import com.geekbrains.knigopoisk.services.contracts.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class BookController implements BookControllerApi {
    private final BookService bookService;

    @Override
    public List<Book> getAllBooks(){
        return bookService.getAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookService.findById(id);
    }

    @Override
    public boolean deleteById(@PathVariable("id") Long id){
        bookService.deleteById(id);
        return true;
    }

    @Override
    public Book createBook(@RequestBody Book book) {
//        book.setId(null);
        return bookService.save(book);
    }
}

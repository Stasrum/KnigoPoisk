package com.geekbrains.knigopoisk.controllers.facade;

import com.geekbrains.knigopoisk.dto.BookDto;
import com.geekbrains.knigopoisk.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

public interface BookControllerApi {

    @GetMapping(value = "/books", produces = "application/json")
    Page<BookDto> getAllBooks(@RequestParam(defaultValue = "1", name = "b") Integer page,
                              @RequestParam Map<String, String> params);

    @GetMapping(value = "/books/delete/{id}")
    boolean deleteById(@PathVariable("id") Long id);

    @PostMapping(value = "/books/add", consumes = "application/json", produces = "application/json")
    Book createBook(@RequestBody Book book);

}

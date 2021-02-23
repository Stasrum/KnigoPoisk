package com.geekbrains.knigopoisk.controllers.facade;

import com.geekbrains.knigopoisk.dto.BookDto;
import com.geekbrains.knigopoisk.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.Optional;

public interface BookControllerApi {

    @GetMapping(value = "/books", produces = "application/json")
    Page<BookDto> getAllBooks(@RequestParam(defaultValue = "1", name = "p") Integer page,
                              @RequestParam Map<String, String> params,
                              @RequestParam (name = "s") int size);

    @GetMapping(value = "/books/{id}")
    BookDto findById(@PathVariable("id") @NotNull Long id);
//
//    @GetMapping(value = "/books/delete/{id}")
//    boolean deleteById(@PathVariable("id") @NotNull Long id);

//    @PostMapping(value = "/books/create", consumes = "application/json", produces = "application/json")
//    BookDto createBook(@RequestBody BookDto book);

}

package com.geekbrains.knigopoisk.controllers.facade;

import com.geekbrains.knigopoisk.entities.Author;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface AuthorControllerApi {

    @GetMapping(value = "/authors", produces = "application/json")
    List<Author> getAllAuthors();

    @PostMapping(value = "/author/add", consumes = "application/json", produces = "application/json")
    Author createAuthor(@RequestBody Author author);
}

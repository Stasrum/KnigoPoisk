package com.geekbrains.knigopoisk.controllers.facade;

import com.geekbrains.knigopoisk.entities.Author;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public interface AuthorControllerApi {

    @GetMapping(value = "/authors", produces = "application/json")
    List<Author> getAllAuthors();

    @PostMapping(path = "/author/add", consumes = "application/json", produces = "application/json")
    Author createAuthor(@RequestBody Author author);
}

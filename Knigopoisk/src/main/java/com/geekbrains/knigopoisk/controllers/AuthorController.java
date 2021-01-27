package com.geekbrains.knigopoisk.controllers;

import com.geekbrains.knigopoisk.entities.Author;
import com.geekbrains.knigopoisk.services.impl.AuthorServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorServiceImpl authorService;

    @GetMapping(value = "/authors", produces = "application/json")
    public List<Author> getAllAuthors() {
        return authorService.getAll();
    }

    @PostMapping(path = "/author/add", consumes = "application/json", produces = "application/json")
    public Author createAuthor(@RequestBody Author author) {

        return authorService.save(author);
    }

}

package com.geekbrains.knigopoisk.controllers;

import com.geekbrains.knigopoisk.entities.Author;
import com.geekbrains.knigopoisk.services.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping(value = "/authors", produces = "application/json")
    public List<Author> getAllAuthors() {
        return authorService.getAll();
    }

    @PostMapping(path = "/author/add", consumes = "application/json", produces = "application/json")
    public Object createAuthor(@Valid @RequestBody Author author) {
        try {
            return authorService.save(author);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }
}

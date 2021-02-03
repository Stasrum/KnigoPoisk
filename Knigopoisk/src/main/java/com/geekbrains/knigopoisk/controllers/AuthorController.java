package com.geekbrains.knigopoisk.controllers;

import com.geekbrains.knigopoisk.controllers.facade.AuthorControllerApi;
import com.geekbrains.knigopoisk.entities.Author;
import com.geekbrains.knigopoisk.services.contracts.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.validation.annotation.Validated;

@RequiredArgsConstructor
@RestController
@Validated
public class AuthorController implements AuthorControllerApi {
    private final AuthorService authorService;

    @Override
    public List<Author> getAllAuthors() {
        return authorService.getAll();
    }

    @Override
    public Author createAuthor(@RequestBody @Valid Author author) {
        return authorService.save(author);
    }

}

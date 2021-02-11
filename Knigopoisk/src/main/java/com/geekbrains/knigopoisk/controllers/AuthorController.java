package com.geekbrains.knigopoisk.controllers;

import com.geekbrains.knigopoisk.controllers.facade.AuthorControllerApi;
import com.geekbrains.knigopoisk.dto.AuthorDto;
import com.geekbrains.knigopoisk.entities.Author;
import com.geekbrains.knigopoisk.services.contracts.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RequiredArgsConstructor
@RestController
public class AuthorController implements AuthorControllerApi {
    private final AuthorService authorService;

    @Override
    public List<AuthorDto> getAllAuthors() {
        return authorService.getAll();
    }

    @Override
    public AuthorDto createAuthor(@RequestBody @Valid AuthorDto author) {
        return authorService.save(author);
    }

}

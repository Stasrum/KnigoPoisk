package com.geekbrains.knigopoisk.controllers.facade;

import com.geekbrains.knigopoisk.dto.AuthorDto;
import com.geekbrains.knigopoisk.entities.Author;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface AuthorControllerApi {

    @GetMapping(value = "/authors", produces = "application/json")
    List<AuthorDto> getAllAuthors();

    @PostMapping(value = "/author/create", consumes = "application/json", produces = "application/json")
    AuthorDto createAuthor(@RequestBody @Valid AuthorDto author);
}

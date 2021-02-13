package com.geekbrains.knigopoisk.controllers.facade;

import com.geekbrains.knigopoisk.dto.AuthorDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/v1")
public interface AuthorControllerApi {

    @GetMapping(value = "/authors", produces = "application/json")
    List<AuthorDto> getAllAuthors();

    @PostMapping(value = "/author/create", consumes = "application/json", produces = "application/json")
    AuthorDto createAuthor(@RequestBody @Valid AuthorDto author);

    @PutMapping(value = "/author/update", consumes = "application/json", produces = "application/json")
    AuthorDto updateAuthor(@RequestBody @Valid AuthorDto authorDto);

    @DeleteMapping("/author/{id}")
    boolean deleteAuthorById(@PathVariable Long id);
}

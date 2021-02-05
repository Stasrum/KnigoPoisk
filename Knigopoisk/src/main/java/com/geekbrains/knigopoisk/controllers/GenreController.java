package com.geekbrains.knigopoisk.controllers;

import com.geekbrains.knigopoisk.controllers.facade.GenreControllerApi;
import com.geekbrains.knigopoisk.entities.Genre;
import com.geekbrains.knigopoisk.services.contracts.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RequiredArgsConstructor
@RestController
public class GenreController implements GenreControllerApi {
    private final GenreService genreService;

    @Override
    public List<Genre> getAllGenres() {
        return genreService.getAll();
    }

    @Override
    public Genre createGenre(@RequestBody @Valid Genre genre) {
        return genreService.save(genre);
    }
}

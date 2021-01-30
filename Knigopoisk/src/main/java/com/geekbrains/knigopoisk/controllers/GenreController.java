package com.geekbrains.knigopoisk.controllers;

import com.geekbrains.knigopoisk.controllers.facade.GenreControllerApi;
import com.geekbrains.knigopoisk.entities.Genre;
import com.geekbrains.knigopoisk.services.contracts.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
public class GenreController implements GenreControllerApi {
    private final GenreService genreService;

    @Override
    public List<Genre> getAllGenres() {
        return genreService.getAll();
    }

    @Override
    public Genre createGenre(@RequestBody Genre genre) {
        return genreService.save(genre);
    }
}

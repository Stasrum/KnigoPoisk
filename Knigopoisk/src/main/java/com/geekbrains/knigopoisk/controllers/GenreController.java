package com.geekbrains.knigopoisk.controllers;

import com.geekbrains.knigopoisk.entities.Genre;
import com.geekbrains.knigopoisk.services.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class GenreController {
    private final GenreService genreService;

    @GetMapping(value = "/genres", produces = "application/json")
    public List<Genre> getAllGenres() {
        return genreService.getAll();
    }

    @PostMapping(path="/genre/add", consumes = "application/json", produces = "application/json")
    public Genre createGenre(@RequestBody Genre genre) {
        genre.setId(null);
        return genreService.save(genre);
    }
}

package com.geekbrains.knigopoisk.controllers.facade;

import com.geekbrains.knigopoisk.entities.Genre;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface GenreControllerApi {
    @GetMapping(value = "/genres", produces = "application/json")
    List<Genre> getAllGenres();

    @PostMapping(value ="/genre/add", consumes = "application/json", produces = "application/json")
    Genre createGenre(@RequestBody Genre genre);
}

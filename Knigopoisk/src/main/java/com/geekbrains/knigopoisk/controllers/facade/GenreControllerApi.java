package com.geekbrains.knigopoisk.controllers.facade;

import com.geekbrains.knigopoisk.dto.GenreDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface GenreControllerApi {
    @GetMapping(value = "/genres", produces = "application/json")
    List<GenreDto> getAllGenres();

    @PostMapping(value ="/genre/create", consumes = "application/json", produces = "application/json")
    GenreDto createGenre(@RequestBody @Valid GenreDto genreDto);
}

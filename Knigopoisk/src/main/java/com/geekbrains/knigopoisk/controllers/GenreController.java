package com.geekbrains.knigopoisk.controllers;

import com.geekbrains.knigopoisk.controllers.facade.GenreControllerApi;
import com.geekbrains.knigopoisk.dto.GenreDto;
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
    public List<GenreDto> getAllGenres() {
        return genreService.getAll();
    }

    @Override
    public GenreDto createGenre(@RequestBody @Valid GenreDto genreDto) {
        return genreService.save(genreDto);
    }

    @Override
    public GenreDto updateGenre(@RequestBody @Valid GenreDto genreDto) {
        return genreService.update(genreDto);
    }

    @Override
    public boolean deleteGenreById(@PathVariable Long id) {
        return genreService.deleteById(id);
    }
}

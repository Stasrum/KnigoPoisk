package com.geekbrains.knigopoisk.services.contracts;

import com.geekbrains.knigopoisk.dto.GenreDto;

import java.util.List;

public interface GenreService {

    List<GenreDto> getAll();

    GenreDto save(GenreDto genreDto);

    GenreDto update(GenreDto genreDto);

    boolean deleteById(Long id);
}

package com.geekbrains.knigopoisk.services.contracts;

import com.geekbrains.knigopoisk.entities.Genre;

import java.util.List;

public interface GenreService {

    List<Genre> getAll();

    Genre save(Genre genre);

    Genre update(Genre genre);
}

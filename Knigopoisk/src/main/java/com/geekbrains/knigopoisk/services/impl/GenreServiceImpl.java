package com.geekbrains.knigopoisk.services.impl;

import com.geekbrains.knigopoisk.entities.Genre;
import com.geekbrains.knigopoisk.repositories.GenreRepository;
import com.geekbrains.knigopoisk.services.contracts.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    @Override
    public List<Genre> getAll() {
        return genreRepository.findAll();
    }

    @Override
    public Genre save(Genre genre) {
        genre.setId(null);
        return genreRepository.save(genre);
    }

    @Override
    public Genre update(Genre genre) {
        genre.setUpdated(OffsetDateTime.now());
        return genreRepository.save(genre);
    }
}

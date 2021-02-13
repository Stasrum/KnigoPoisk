package com.geekbrains.knigopoisk.services.impl;

import com.geekbrains.knigopoisk.dto.GenreDto;
import com.geekbrains.knigopoisk.entities.Genre;
import com.geekbrains.knigopoisk.exceptions.GenreNotFoundException;
import com.geekbrains.knigopoisk.repositories.GenreRepository;
import com.geekbrains.knigopoisk.services.contracts.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    @Override
    public List<GenreDto> getAll() {
        List<Genre> genres = genreRepository.findAllByOrderByNameAsc();
        return genres.stream().map(GenreDto::new).collect(Collectors.toList());
    }

    @Override
    public GenreDto save(GenreDto genreDto) {
        Genre g = GenreDto.fromDto(genreDto);
        g.setId(null);
        Genre genre = genreRepository.save(g);
        return new GenreDto(genre);
    }

    @Override
    public GenreDto update(GenreDto genreDto) {
        Genre g = genreRepository.findOneByName(genreDto.getName()).orElseThrow(()-> new GenreNotFoundException("Genre isn't found"));
        g.setUpdated(OffsetDateTime.now());
        Genre genre = genreRepository.save(g);
        return new GenreDto(genre);
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<Genre> genre = genreRepository.findById(id);
        if (!genre.isPresent()) {
            throw new GenreNotFoundException("Жанр с id = " + id + " не существует");
        }
        genreRepository.delete(genre.get());
        return true;
    }
}

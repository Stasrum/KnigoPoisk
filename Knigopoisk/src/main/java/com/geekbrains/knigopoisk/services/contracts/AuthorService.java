package com.geekbrains.knigopoisk.services.contracts;

import com.geekbrains.knigopoisk.dto.AuthorDto;
import com.geekbrains.knigopoisk.entities.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<AuthorDto> getAll();

    AuthorDto save(AuthorDto authorDto);

    AuthorDto update(AuthorDto authorDto);

    boolean deleteById(Long id);
}

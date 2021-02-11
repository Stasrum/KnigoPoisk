package com.geekbrains.knigopoisk.services.contracts;

import com.geekbrains.knigopoisk.dto.AuthorDto;
import com.geekbrains.knigopoisk.entities.Author;

import java.util.List;

public interface AuthorService {
    List<AuthorDto> getAll();

    AuthorDto save(AuthorDto authorDto);

    AuthorDto update(AuthorDto authorDto);
}

package com.geekbrains.knigopoisk.services.contracts;

import com.geekbrains.knigopoisk.dto.AuthorDto;

import java.util.List;

public interface AuthorService {
    List<AuthorDto> getAll();

    AuthorDto save(AuthorDto authorDto);

    AuthorDto update(AuthorDto authorDto);

    boolean deleteById(Long id);
}

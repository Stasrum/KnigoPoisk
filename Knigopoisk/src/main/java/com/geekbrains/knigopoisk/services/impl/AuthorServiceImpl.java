package com.geekbrains.knigopoisk.services.impl;

import com.geekbrains.knigopoisk.dto.AuthorDto;
import com.geekbrains.knigopoisk.entities.Author;
import com.geekbrains.knigopoisk.exceptions.AuthorNotFoundException;
import com.geekbrains.knigopoisk.repositories.AuthorRepository;
import com.geekbrains.knigopoisk.services.contracts.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Override
    public List<AuthorDto> getAll() {
        List<Author> authors = authorRepository.findAllByOrderByNameAsc();
        return authors.stream().map(AuthorDto::new).collect(Collectors.toList());
    }

    @Override
    public AuthorDto save(AuthorDto authorDto) {
        Author a = AuthorDto.fromDto(authorDto);
        a.setId(null);
        Author author = authorRepository.save(a);
        return new AuthorDto(author);
    }

    @Override
    public AuthorDto update(AuthorDto authorDto) {
        Author a = authorRepository.findOneByName(authorDto.getName()).orElseThrow(()-> new AuthorNotFoundException("Author isn't found"));
        a.setUpdated(OffsetDateTime.now());
        Author author = authorRepository.save(a);
        return new AuthorDto(author);
    }
}

package com.geekbrains.knigopoisk.services.impl;

import com.geekbrains.knigopoisk.entities.Author;
import com.geekbrains.knigopoisk.repositories.AuthorRepository;
import com.geekbrains.knigopoisk.services.contracts.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author save(Author author) {
        author.setId(null); // ?
        return authorRepository.save(author);
    }
}

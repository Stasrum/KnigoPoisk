package com.geekbrains.knigopoisk.services.impl;

import com.geekbrains.knigopoisk.entities.Author;
import com.geekbrains.knigopoisk.repositories.AuthorRepository;
import com.geekbrains.knigopoisk.services.contracts.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class
AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Override
    public List<Author> getAll() {
        return authorRepository.findAllByOrderByNameAsc();
    }

    @Override
    public Author save(Author author) {
        author.setId(null);
        return authorRepository.save(author);
    }

    @Override
    public Author update(Author author) {
        author.setUpdated(OffsetDateTime.now());
        return authorRepository.save(author);
    }
}

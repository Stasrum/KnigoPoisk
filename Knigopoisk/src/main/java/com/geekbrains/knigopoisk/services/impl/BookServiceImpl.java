package com.geekbrains.knigopoisk.services.impl;

import com.geekbrains.knigopoisk.entities.Book;
import com.geekbrains.knigopoisk.repositories.BookRepository;
import com.geekbrains.knigopoisk.services.contracts.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public List<Book> getAll(){
        return bookRepository.findAll();
    }

    @Override
    public Book save(Book book){
        return bookRepository.save(book);
    }

    @Override
    public boolean deleteById(Long id){
        bookRepository.deleteById(id);
        return true;
    }

    @Override
    public Page<Book> findAll(Specification<Book> spec, int page, int size) {
        return bookRepository.findAll(spec, PageRequest.of(page, size));
    }

}

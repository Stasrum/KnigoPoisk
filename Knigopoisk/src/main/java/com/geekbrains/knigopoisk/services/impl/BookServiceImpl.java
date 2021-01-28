package com.geekbrains.knigopoisk.services.impl;

import com.geekbrains.knigopoisk.entities.Book;
import com.geekbrains.knigopoisk.repositories.BookRepository;
import com.geekbrains.knigopoisk.services.contracts.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void deleteById(Long id){
        bookRepository.deleteById(id);
    }
}

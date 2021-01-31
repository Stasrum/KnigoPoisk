package com.geekbrains.knigopoisk.services.impl;

import com.geekbrains.knigopoisk.entities.Book;
import com.geekbrains.knigopoisk.exceptions.BookNotFoundException;
import com.geekbrains.knigopoisk.repositories.BookRepository;
import com.geekbrains.knigopoisk.services.contracts.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Book> book = bookRepository.findById(id);
        if (!book.isPresent()) {
            throw new BookNotFoundException("Книга с id = " + id + " не существует");
        }
        bookRepository.delete(book.get());
        //bookRepository.deleteById(id);
        return true;
    }
}

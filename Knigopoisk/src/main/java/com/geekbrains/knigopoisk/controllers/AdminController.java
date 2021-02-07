package com.geekbrains.knigopoisk.controllers;

import com.geekbrains.knigopoisk.controllers.facade.AdminControllerApi;
import com.geekbrains.knigopoisk.dto.BookDto;
import com.geekbrains.knigopoisk.dto.UserForAdminsEditDto;
import com.geekbrains.knigopoisk.entities.Book;
import com.geekbrains.knigopoisk.entities.User;
import com.geekbrains.knigopoisk.services.contracts.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AdminController implements AdminControllerApi {

    private BookService bookService;

    @Override
    public ResponseEntity<Book> addBook(BookDto bookDto) {
        return null;
    }

    @Override
    public ResponseEntity<Book> getBookById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> editBook(BookDto bookDto) {
        return null;
    }

    @Override
    public ResponseEntity<User> getUserById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> editUser(UserForAdminsEditDto userDto) {
        return null;
    }
}

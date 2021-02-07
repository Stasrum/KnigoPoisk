package com.geekbrains.knigopoisk.controllers;

import com.geekbrains.knigopoisk.controllers.facade.AdminControllerApi;
import com.geekbrains.knigopoisk.dto.BookDto;
import com.geekbrains.knigopoisk.dto.UserForAdminsEditDto;
import com.geekbrains.knigopoisk.entities.Book;
import com.geekbrains.knigopoisk.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController implements AdminControllerApi {

    @Override
    public void addBook(BookDto bookDto) {

    }

    @Override
    public ResponseEntity<Book> getBookById(Long id) {
        return null;
    }

    @Override
    public void editBook(BookDto bookDto) {

    }

    @Override
    public ResponseEntity<User> getUserById(Long id) {
        return null;
    }

    @Override
    public void editUser(UserForAdminsEditDto userDto) {

    }
}

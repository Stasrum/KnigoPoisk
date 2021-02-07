package com.geekbrains.knigopoisk.controllers;

import com.geekbrains.knigopoisk.controllers.facade.AdminControllerApi;
import com.geekbrains.knigopoisk.dto.BookDto;
import com.geekbrains.knigopoisk.dto.UserForAdminsEditDto;
import com.geekbrains.knigopoisk.entities.Book;
import com.geekbrains.knigopoisk.entities.User;
import com.geekbrains.knigopoisk.responsies.ReqErrorResponse;
import com.geekbrains.knigopoisk.services.contracts.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
public class AdminController implements AdminControllerApi {

    @Autowired
    private BookService bookService;

    @Override
    public ResponseEntity<?> addBook(@RequestBody @Valid BookDto bookDto) {
        if (bookDto==null){
            log.warn("Request for book adding is empty");
            return new ResponseEntity<>(new ReqErrorResponse(HttpStatus.BAD_REQUEST.value(), "Request for book adding is empty"), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(bookService.add(bookDto));
    }

    @Override
    public ResponseEntity<Book> getBookById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> editBook(@RequestBody @Valid BookDto bookDto) {
        if (bookDto==null){
            log.warn("Request for book editing is empty");
            return new ResponseEntity<>(new ReqErrorResponse(HttpStatus.BAD_REQUEST.value(), "Request for book editing is empty"), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(bookService.edit(bookDto));
    }

    @Override
    public ResponseEntity<User> getUserById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<User> editUser(@RequestBody UserForAdminsEditDto userDto) {
        return null;
    }
}

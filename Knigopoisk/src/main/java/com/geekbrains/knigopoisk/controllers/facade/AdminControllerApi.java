package com.geekbrains.knigopoisk.controllers.facade;

import com.geekbrains.knigopoisk.dto.BookDto;
import com.geekbrains.knigopoisk.dto.UserForAdminsEditDto;
import com.geekbrains.knigopoisk.entities.Book;
import com.geekbrains.knigopoisk.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/admin")
public interface AdminControllerApi {

    @PostMapping("/books/add")
    ResponseEntity<?> addBook(BookDto bookDto);

    @GetMapping("/books/{id}")
    ResponseEntity<Book> getBookById(@PathVariable("id") Long id);

    @PostMapping("/books/edit")
    ResponseEntity<?> editBook(BookDto bookDto);

    @GetMapping("/users/{id}")
    ResponseEntity<User> getUserById(@PathVariable("id") Long id);

    @PostMapping("/users/edit")
    ResponseEntity<?> editUser(UserForAdminsEditDto userDto);
}
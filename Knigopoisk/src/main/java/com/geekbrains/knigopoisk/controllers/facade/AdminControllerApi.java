package com.geekbrains.knigopoisk.controllers.facade;

import com.geekbrains.knigopoisk.dto.BookDto;
import com.geekbrains.knigopoisk.dto.UserForAdminsEditDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/admin")
public interface AdminControllerApi {

    @PostMapping("/books/add")
    ResponseEntity<?> addBook(@RequestBody BookDto bookDto);

    @GetMapping("/books/{id}")
    ResponseEntity<?> getBookById(@PathVariable("id") Long id);

    @PostMapping("/books/edit")
    ResponseEntity<?> editBook(@RequestBody BookDto bookDto);

    @GetMapping("/users/{id}")
    ResponseEntity<?> getUserById(@PathVariable("id") Long id);

    @PostMapping("/users/edit")
    ResponseEntity<?> editUser(@RequestBody UserForAdminsEditDto userDto);
}
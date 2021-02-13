package com.geekbrains.knigopoisk.controllers.facade;

import com.geekbrains.knigopoisk.dto.BookDto;
import com.geekbrains.knigopoisk.dto.UserForAdminsEditDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Map;

@RequestMapping("/api/v1/admin")
public interface AdminControllerApi {

    @PostMapping("/books/add")
    ResponseEntity<?> addBook(@RequestBody BookDto bookDto);

    @GetMapping("/books/{id}")
    ResponseEntity<?> getBookById(@PathVariable("id") @NotNull Long id);

    @DeleteMapping("/books/delete/{id}")
    ResponseEntity<?> deleteBookById(@PathVariable("id") @NotNull Long id);

    @PostMapping("/books/edit")
    ResponseEntity<?> editBook(@RequestBody BookDto bookDto);

    @GetMapping("/users")
    ResponseEntity<?> getAllUsers(Integer page,
                                  @RequestParam Map<String, String> params,
                                  Integer size);

    @GetMapping("/users/{id}")
    ResponseEntity<?> getUserById(@PathVariable("id") @NotNull Long id);

    @DeleteMapping("/users/delete/{id}")
    ResponseEntity<?> deleteUserById(@PathVariable("id") @NotNull Long id);

    @PostMapping("/users/edit")
    ResponseEntity<?> editUsersRights(@RequestBody UserForAdminsEditDto userDto);
}

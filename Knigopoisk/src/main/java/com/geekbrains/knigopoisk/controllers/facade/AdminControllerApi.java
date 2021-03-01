package com.geekbrains.knigopoisk.controllers.facade;

import com.geekbrains.knigopoisk.dto.BookDto;
import com.geekbrains.knigopoisk.dto.SubscriptionDto;
import com.geekbrains.knigopoisk.dto.UserForAdminsEditDto;
import com.geekbrains.knigopoisk.entities.Book;
import com.geekbrains.knigopoisk.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.io.IOException;
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

    @PostMapping("/books/addImage/{id}")
    ResponseEntity<?> addBookImage(@PathVariable("id") @NotNull Long id, @RequestParam(value="file") MultipartFile file) throws IOException;

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

    @PostMapping("/subscribtions/send")
    ResponseEntity<?> sendLastWeekBooksMail(@RequestBody SubscriptionDto subscriptionDto);
}

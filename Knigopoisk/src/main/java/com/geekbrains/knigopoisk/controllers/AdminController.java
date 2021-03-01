package com.geekbrains.knigopoisk.controllers;

import com.geekbrains.knigopoisk.controllers.facade.AdminControllerApi;
import com.geekbrains.knigopoisk.dto.BookDto;
import com.geekbrains.knigopoisk.dto.SubscriptionDto;
import com.geekbrains.knigopoisk.dto.UserForAdminsEditDto;
import com.geekbrains.knigopoisk.entities.Book;
import com.geekbrains.knigopoisk.entities.BookImage;
import com.geekbrains.knigopoisk.exceptions.ApiError;
import com.geekbrains.knigopoisk.services.contracts.BookImageService;
import com.geekbrains.knigopoisk.services.contracts.BookService;
import com.geekbrains.knigopoisk.services.contracts.UserService;
import com.geekbrains.knigopoisk.services.impl.MailServiceImpl;
import com.geekbrains.knigopoisk.util.UserFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AdminController implements AdminControllerApi {

    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;
    @Autowired
    private MailServiceImpl mailService;

    private final BookImageService bookImageService;

    @Override
    public ResponseEntity<?> addBook(@RequestBody  @NotNull @Valid BookDto bookDto) {
        return ResponseEntity.ok(bookService.add(bookDto));
    }

    @Override
    public ResponseEntity<?> getBookById(@PathVariable("id") @NotNull Long id) {
        return ResponseEntity.ok(bookService.findById(id));
    }

    @Override
    public ResponseEntity<?> deleteBookById(@PathVariable("id") @NotNull Long id) {
        return ResponseEntity.ok(bookService.deleteById(id));
    }

    @Override
    public ResponseEntity<?> editBook(@RequestBody @Valid BookDto bookDto) {
        return ResponseEntity.ok(bookService.edit(bookDto));
    }

    @Override
    public ResponseEntity<?> addBookImage(@NotNull Long id, MultipartFile file) throws IOException {
        if (file == null) {
            ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Попытка загрузки пустого файла", "Попытка загрузки пустого файла");
            return new ResponseEntity<>(apiError, apiError.getStatus());
        }

        Book book = bookService.findBookById(id);
        BookImage bookImage = bookImageService.addBookImage(book, file);
        Map<String, String> props = new HashMap<>();
        props.put("image_path", bookImage.getPath());

        return ResponseEntity.ok(props);
    }

    @Override
    public ResponseEntity<?> getAllUsers(Integer page, Map<String, String> params, Integer size) {
        if (page==null||page < 1) page = 1;
        if (size==null||size <= 0) size = 100;

        UserFilter userFilter = new UserFilter(params);
        return ResponseEntity.ok(userService.getAll(userFilter.getSpec(), page - 1, size));
    }

    @Override
    public ResponseEntity<?> getUserById(@PathVariable("id") @NotNull Long id) {
        return ResponseEntity.ok(userService.findOneForAdminByUserId(id));
    }

    @Override
    public ResponseEntity<?> deleteUserById(@PathVariable("id") @NotNull Long id) {
        return ResponseEntity.ok(userService.deleteByUserId(id));
    }

    @Override
    public ResponseEntity<?> editUsersRights(@RequestBody UserForAdminsEditDto userDto) {
        return ResponseEntity.ok(userService.editUsersRights(userDto));
    }

    @Override
    public ResponseEntity<?> sendLastWeekBooksMail(@RequestBody SubscriptionDto subscriptionDto) {
        System.out.println(subscriptionDto);
        mailService.sendLastWeekBooksMail(subscriptionDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

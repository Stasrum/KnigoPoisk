package com.geekbrains.knigopoisk.controllers;

import com.geekbrains.knigopoisk.controllers.facade.BookControllerApi;
import com.geekbrains.knigopoisk.dto.BookDto;
import com.geekbrains.knigopoisk.entities.Book;
import com.geekbrains.knigopoisk.services.contracts.BookService;
import com.geekbrains.knigopoisk.util.BookFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@RestController
public class BookController implements BookControllerApi {
    private final BookService bookService;

    @Override
    public Page<BookDto> getAllBooks(Integer page, Map<String, String> params) {
        if (page < 1) {
            page = 1;
        }

        BookFilter bookFilter = new BookFilter(params);
        Page<Book> content = bookService.findAll(bookFilter.getSpec(), page - 1, 10);
        Page<BookDto> out = new PageImpl<>(content.getContent().stream().map(BookDto::new).collect(Collectors.toList()), content.getPageable(), content.getTotalElements());

        return out;
    }

    @Override
    public Optional<Book> findById(@PathVariable("id") @NotNull Long id) {
        return bookService.findById(id);
    }

    @Override
    public boolean deleteById(@PathVariable("id") @NotNull Long id){
        bookService.deleteById(id);
        return true;
    }

    @Override
    public Book createBook(@RequestBody Book book) {
        return bookService.save(book);
    }
}

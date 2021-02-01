package com.geekbrains.knigopoisk.dto;

import com.geekbrains.knigopoisk.entities.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BookDto {
    private Long id;
    private LocalDateTime created;
    private LocalDateTime updated;
    private Author author;
    private int year;
    private Language lang;
    private Genre genre;
    private Publisher publisher;
    private String description;

    @NotNull(message = "title must be not null")
    @Size(min = 4, max = 255, message = "4 - 255 symbols")
    private String title;

    @Size(min = 13, max = 13, message = "13 symbols")
    private String isbn;

    public BookDto(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.created = book.getCreated();
        this.updated = book.getUpdated();
        this.author = book.getAuthor();
        this.year = book.getYear();
        this.lang = book.getLang();
        this.genre = book.getGenre();
        this.publisher = book.getPublisher();
        this.description = book.getDescription();
        this.isbn = book.getIsbn();
    }
}

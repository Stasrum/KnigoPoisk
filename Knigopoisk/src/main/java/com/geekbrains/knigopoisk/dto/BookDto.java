package com.geekbrains.knigopoisk.dto;

import com.geekbrains.knigopoisk.entities.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
public class BookDto {
    private Long id;
    private OffsetDateTime created;
    private OffsetDateTime updated;
    private List<Author> authors;
    private int year;
    private List<Language> languages;
    private List<Genre> genres;
    private List<Publisher> publishers;
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
        this.authors = book.getAuthors();
        this.year = book.getYear();
        this.languages = book.getLanguages();
        this.genres = book.getGenres();
        this.publishers = book.getPublishers();
        this.description = book.getDescription();
        this.isbn = book.getIsbn();
    }
}

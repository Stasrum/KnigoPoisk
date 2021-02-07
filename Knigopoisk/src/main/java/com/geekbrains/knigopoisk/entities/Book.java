package com.geekbrains.knigopoisk.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;

@Entity(name = "Book")
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book extends DefaultEntity {

    @NotNull(message = "title must be not null")
    @Column(name = "title")
    @Size(min = 4, max = 255, message = "4 - 255 symbols")
    private String title;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "books_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors;

    @Column(name = "year")
    private int year;

    @Column(name = "isbn")
    @Size(min = 13, max = 13, message = "13 symbols")
    private String isbn;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "books_languages",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id"))
    private List<Language> languages;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "books_genres",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "books_publishers",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "publisher_id"))
    private List<Publisher> publishers;

    @Column(name = "description")
    private String description;

    // Заглушки
    @Column(name = "author_id")
    private int authorId = 1;
    @Column(name = "publisher_id")
    private int publisherId = 1;
    @Column(name = "lang_id")
    private int langId = 1;
    @Column(name = "genre_id")
    private int genreId = 1;
}

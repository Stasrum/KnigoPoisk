package com.geekbrains.knigopoisk.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @Column(name = "year")
    private int year;

    @Column(name = "isbn")
    @Size(min = 13, max = 13, message = "13 symbols")
    private String isbn;

    @ManyToOne
    @JoinColumn(name = "lang_id")
    private Language lang;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @Column(name = "description")
    private String description;
}

package com.geekbrains.knigopoisk.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity(name = "Language")
@Table(name = "languages")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Language extends DefaultEntity {

    @NotNull(message = "Language name must be not null")
    @Column(name = "name")
    private String name;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "books_languages",
            joinColumns = @JoinColumn(name = "language_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Collection<Book> books;
}
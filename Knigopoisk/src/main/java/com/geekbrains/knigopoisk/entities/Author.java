package com.geekbrains.knigopoisk.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;


@Entity(name = "Author")
@Table(name = "authors")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Author extends DefaultEntity {

    @NotNull(message = "author name must be not null")
    @Column(name = "name")
    @Size(min = 3, max = 45, message = "3 - 45 symbols")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "books_authors",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Collection<Book> books;

}

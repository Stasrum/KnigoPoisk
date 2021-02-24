package com.geekbrains.knigopoisk.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "Genre")
@Table(name = "genres")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Genre extends DefaultEntity {

    @NotNull(message = "genre name must be not null")
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "books_genres",
            joinColumns = @JoinColumn(name = "genre_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Collection<Book> books;
}

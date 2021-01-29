package com.geekbrains.knigopoisk.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity(name = "Author")
@Table(name = "authors")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Author extends DafaultEntity {

    @NotNull(message = "author name must be not null")
    @Column(name = "name")
    @Size(min = 3 , max = 45, message = "3 - 45 symbols")
    private String name;

}
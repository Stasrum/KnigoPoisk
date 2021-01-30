package com.geekbrains.knigopoisk.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "authors")
@Getter
@Setter
@NoArgsConstructor

public class Author extends DefaultEntity {


    @Column(unique = true, nullable = false, name = "name")
    private String name;
}

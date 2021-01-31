package com.geekbrains.knigopoisk.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
}

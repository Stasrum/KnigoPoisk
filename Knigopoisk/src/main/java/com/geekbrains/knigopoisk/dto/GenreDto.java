package com.geekbrains.knigopoisk.dto;

import com.geekbrains.knigopoisk.entities.Genre;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GenreDto {
    private Long id;
    private String name;

    public GenreDto(Genre genre) {
        this.id = genre.getId();
        this.name = genre.getName();
    }
}

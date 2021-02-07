package com.geekbrains.knigopoisk.dto;

import com.geekbrains.knigopoisk.entities.Author;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthorDto {
    private Long id;
    private String name;

    public AuthorDto(Author author) {
        this.id = author.getId();
        this.name = author.getName();
    }
}

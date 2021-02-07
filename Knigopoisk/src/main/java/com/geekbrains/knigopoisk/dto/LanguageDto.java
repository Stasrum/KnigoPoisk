package com.geekbrains.knigopoisk.dto;

import com.geekbrains.knigopoisk.entities.Language;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LanguageDto {
    private Long id;
    private String name;

    public LanguageDto(Language language) {
        this.id = language.getId();
        this.name = language.getName();
    }
}

package com.geekbrains.knigopoisk.services.contracts;

import com.geekbrains.knigopoisk.dto.LanguageDto;
import com.geekbrains.knigopoisk.entities.Language;

import java.util.List;

public interface LanguageService {

    List<LanguageDto> getAll();

    LanguageDto save(LanguageDto languageDto);

    LanguageDto update(LanguageDto languageDto);
}

package com.geekbrains.knigopoisk.services.contracts;

import com.geekbrains.knigopoisk.dto.LanguageDto;;

import java.util.List;

public interface LanguageService {

    List<LanguageDto> getAll();

    LanguageDto save(LanguageDto languageDto);

    LanguageDto update(LanguageDto languageDto);

    boolean deleteById(Long id);
}

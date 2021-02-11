package com.geekbrains.knigopoisk.controllers.facade;

import com.geekbrains.knigopoisk.dto.LanguageDto;
import com.geekbrains.knigopoisk.entities.Language;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface LanguageControllerApi {
    @GetMapping(value = "/languages", produces = "application/json")
        // handlers, advices
    List<LanguageDto> getAllLanguages(); // Throws ваши кастомные исключения

    @PostMapping(value ="/language/create", consumes = "application/json", produces = "application/json")
    LanguageDto createLanguage(@RequestBody @Valid LanguageDto language);
}

package com.geekbrains.knigopoisk.controllers.facade;

import com.geekbrains.knigopoisk.dto.LanguageDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RequestMapping("/api/v1")
public interface LanguageControllerApi {
    @GetMapping(value = "/languages", produces = "application/json")
        // handlers, advices
    List<LanguageDto> getAllLanguages(); // Throws ваши кастомные исключения

    @PostMapping(value ="/language/create", consumes = "application/json", produces = "application/json")
    LanguageDto createLanguage(@RequestBody @Valid LanguageDto language);

    @PutMapping(value = "/language/update", consumes = "application/json", produces = "application/json")
    LanguageDto updateLanguage(@RequestBody @Valid LanguageDto languageDto);

    @DeleteMapping("/language/{id}")
    boolean deleteLanguageById(@PathVariable Long id);
}

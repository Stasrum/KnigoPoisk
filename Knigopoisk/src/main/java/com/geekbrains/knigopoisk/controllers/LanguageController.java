package com.geekbrains.knigopoisk.controllers;

import com.geekbrains.knigopoisk.entities.Language;
import com.geekbrains.knigopoisk.services.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class LanguageController {
    private final LanguageService languageService;

    @GetMapping(value = "/languages", produces = "application/json")
    public List<Language> getAllLanguages() {
        return languageService.getAll();
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Language createLanguage(@RequestBody Language language) {
        language.setId(null);
        return languageService.save(language);
    }
}

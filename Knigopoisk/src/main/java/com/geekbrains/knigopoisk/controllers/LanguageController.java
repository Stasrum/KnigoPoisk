package com.geekbrains.knigopoisk.controllers;

import com.geekbrains.knigopoisk.controllers.facade.LanguageControllerApi;
import com.geekbrains.knigopoisk.entities.Language;
import com.geekbrains.knigopoisk.services.contracts.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class LanguageController implements LanguageControllerApi {
    private final LanguageService languageService;

    @Override
    public List<Language> getAllLanguages() {
        return languageService.getAll();
    }

    @Override
    public Language createLanguage(@RequestBody Language language) {
        return languageService.save(language);
    }
}

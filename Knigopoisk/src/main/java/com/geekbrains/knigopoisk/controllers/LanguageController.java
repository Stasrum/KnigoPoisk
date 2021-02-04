package com.geekbrains.knigopoisk.controllers;

import com.geekbrains.knigopoisk.controllers.facade.LanguageControllerApi;
import com.geekbrains.knigopoisk.entities.Language;
import com.geekbrains.knigopoisk.services.contracts.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RequiredArgsConstructor
@RestController
public class LanguageController implements LanguageControllerApi {
    private final LanguageService languageService;

    @Override
    public List<Language> getAllLanguages() {
        return languageService.getAll();
    }

    @Override
    public Language createLanguage(@RequestBody @Valid Language language) {
        return languageService.save(language);
    }
}

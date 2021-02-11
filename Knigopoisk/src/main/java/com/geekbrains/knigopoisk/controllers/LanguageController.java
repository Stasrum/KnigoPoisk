package com.geekbrains.knigopoisk.controllers;

import com.geekbrains.knigopoisk.controllers.facade.LanguageControllerApi;
import com.geekbrains.knigopoisk.dto.LanguageDto;
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
    public List<LanguageDto> getAllLanguages() {
        return languageService.getAll();
    }

    @Override
    public LanguageDto createLanguage(@RequestBody @Valid LanguageDto language) {
        return languageService.save(language);
    }
}

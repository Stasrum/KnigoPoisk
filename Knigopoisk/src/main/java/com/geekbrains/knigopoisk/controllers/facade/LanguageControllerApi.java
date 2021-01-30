package com.geekbrains.knigopoisk.controllers.facade;

import com.geekbrains.knigopoisk.entities.Language;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public interface LanguageControllerApi {
    @GetMapping(value = "/languages", produces = "application/json")
        // handlers, advices
    List<Language> getAllLanguages(); // Throws ваши кастомные исключения

    @PostMapping(value ="/language/add", consumes = "application/json", produces = "application/json")
    Language createLanguage(@RequestBody Language language);
}

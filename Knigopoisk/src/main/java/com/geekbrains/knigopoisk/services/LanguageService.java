package com.geekbrains.knigopoisk.services;

import com.geekbrains.knigopoisk.entities.Language;
import com.geekbrains.knigopoisk.repositories.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LanguageService {
    private final LanguageRepository languageRepository;

    public List<Language> getAll() {
        return languageRepository.findAll();
    }

    public Language save(Language language) {
        return languageRepository.save(language);
    }
}

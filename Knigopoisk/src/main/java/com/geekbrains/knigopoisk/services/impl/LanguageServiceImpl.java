package com.geekbrains.knigopoisk.services.impl;

import com.geekbrains.knigopoisk.entities.Language;
import com.geekbrains.knigopoisk.repositories.LanguageRepository;
import com.geekbrains.knigopoisk.services.contracts.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {
    private final LanguageRepository languageRepository;

    @Override
    public List<Language> getAll() {
        return languageRepository.findAll();
    }

    @Override
    public Language save(Language language) {
        language.setId(null);
        return languageRepository.save(language);
    }

    @Override
    public Language update(Language language) {
        language.setUpdated(OffsetDateTime.now());
        return languageRepository.save(language);
    }
}

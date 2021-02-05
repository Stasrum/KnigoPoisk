package com.geekbrains.knigopoisk.services.contracts;

import com.geekbrains.knigopoisk.entities.Language;

import java.util.List;

public interface LanguageService {

    List<Language> getAll();

    Language save(Language language);

    Language update(Language language);
}

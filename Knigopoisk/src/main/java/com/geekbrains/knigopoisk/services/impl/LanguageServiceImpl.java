package com.geekbrains.knigopoisk.services.impl;

import com.geekbrains.knigopoisk.dto.LanguageDto;
import com.geekbrains.knigopoisk.entities.Language;
import com.geekbrains.knigopoisk.exceptions.LanguageNotFoundException;
import com.geekbrains.knigopoisk.repositories.LanguageRepository;
import com.geekbrains.knigopoisk.services.contracts.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {
    private final LanguageRepository languageRepository;

    @Override
    public List<LanguageDto> getAll() {
        List<Language> languages = languageRepository.findAllByOrderByNameAsc();
        return languages.stream().map(LanguageDto::new).collect(Collectors.toList());
    }

    @Override
    public LanguageDto save(LanguageDto languageDto) {
        Language l = LanguageDto.fromDto(languageDto);
        l.setId(null);
        Language language = languageRepository.save(l);
        return new LanguageDto(language);
    }

    @Override
    public LanguageDto update(LanguageDto languageDto) {
        Language l = languageRepository.findOneByName(languageDto.getName()).orElseThrow(()-> new LanguageNotFoundException("Language isn't found"));
        l.setUpdated(OffsetDateTime.now());
        Language language = languageRepository.save(l);
        return new LanguageDto(language);
    }
}

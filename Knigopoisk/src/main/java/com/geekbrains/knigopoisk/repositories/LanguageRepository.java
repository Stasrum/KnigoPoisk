package com.geekbrains.knigopoisk.repositories;

import com.geekbrains.knigopoisk.entities.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {
    Optional<Language> findOneByName(String name);

    List<Language> findAllByOrderByNameAsc();
}

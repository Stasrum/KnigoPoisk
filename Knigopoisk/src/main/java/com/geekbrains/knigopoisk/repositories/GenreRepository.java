package com.geekbrains.knigopoisk.repositories;

import com.geekbrains.knigopoisk.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
}

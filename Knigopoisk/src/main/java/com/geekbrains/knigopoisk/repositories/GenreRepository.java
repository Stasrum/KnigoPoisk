package com.geekbrains.knigopoisk.repositories;

import com.geekbrains.knigopoisk.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    Optional<Genre> findOneByName(String name);

    List<Genre> findAllByOrderByNameAsc();
}

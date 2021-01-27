package com.geekbrains.knigopoisk.repositories;

import com.geekbrains.knigopoisk.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}

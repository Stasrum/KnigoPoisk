package com.geekbrains.knigopoisk.repositories;

import com.geekbrains.knigopoisk.entities.BookImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookImageRepository extends JpaRepository<BookImage, Long> {
}

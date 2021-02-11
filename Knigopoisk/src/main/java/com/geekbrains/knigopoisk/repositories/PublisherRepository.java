package com.geekbrains.knigopoisk.repositories;

import com.geekbrains.knigopoisk.entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    Optional<Publisher> findOneByName(String name);

    List<Publisher> findAllByOrderByNameAsc();
}

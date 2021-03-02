package com.geekbrains.knigopoisk.repositories;

import com.geekbrains.knigopoisk.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findAllLikesByBookId(Long id);
}

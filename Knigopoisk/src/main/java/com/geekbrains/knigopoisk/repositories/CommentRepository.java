package com.geekbrains.knigopoisk.repositories;

import com.geekbrains.knigopoisk.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllCommentsByBookId(Long id);
}

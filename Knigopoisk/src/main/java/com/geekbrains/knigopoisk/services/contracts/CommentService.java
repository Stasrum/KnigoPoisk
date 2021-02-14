package com.geekbrains.knigopoisk.services.contracts;

import com.geekbrains.knigopoisk.entities.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAll();

    Comment findById(Long id);

    boolean deleteById(Long id);

    Comment save(Comment comment);

    Comment update(Comment comment);
}

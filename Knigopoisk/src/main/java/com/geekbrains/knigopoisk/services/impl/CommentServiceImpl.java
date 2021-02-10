package com.geekbrains.knigopoisk.services.impl;

import com.geekbrains.knigopoisk.entities.Comment;
import com.geekbrains.knigopoisk.exceptions.CommentNotFoundException;
import com.geekbrains.knigopoisk.repositories.CommentRepository;
import com.geekbrains.knigopoisk.services.contracts.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {


    private CommentRepository commentRepository;

    @Autowired
    public void setCommentRepository(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id).orElseThrow(()->new CommentNotFoundException("Комментарий не с ID=<"+id+"> найден"));
    }

    @Override
    public boolean deleteById(Long id) {
        commentRepository.deleteById(id);
        return true;
    }

    @Override
    public Comment save(Comment comment) {
        comment.setId(null);
        return commentRepository.save(comment);
    }

    @Override
    public Comment update(Comment comment) {
        comment.setUpdated(OffsetDateTime.now());
        return commentRepository.save(comment);
    }
}

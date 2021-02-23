package com.geekbrains.knigopoisk.services.contracts;

import com.geekbrains.knigopoisk.dto.CommentDto;

import java.util.List;

public interface CommentService {
    List<CommentDto> getAll();
    CommentDto findById(Long id);
    CommentDto save(CommentDto commentDto);
    CommentDto update(CommentDto commentDto);
    boolean deleteById(Long id);
    List<CommentDto> findAllCommentsByBookId(Long id);
}
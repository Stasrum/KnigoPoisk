package com.geekbrains.knigopoisk.services.impl;

import com.geekbrains.knigopoisk.dto.CommentDto;
import com.geekbrains.knigopoisk.dto.mappers.CommentMapper;
import com.geekbrains.knigopoisk.entities.Comment;
import com.geekbrains.knigopoisk.exceptions.CommentNotFoundException;
import com.geekbrains.knigopoisk.exceptions.UserNotFoundException;
import com.geekbrains.knigopoisk.repositories.CommentRepository;
import com.geekbrains.knigopoisk.services.contracts.UserService;
import com.geekbrains.knigopoisk.services.contracts.BookService;
import com.geekbrains.knigopoisk.services.contracts.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final BookService bookService;
    private final CommentMapper commentMapper;

    @Override
    public List<CommentDto> getAll() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream().map(CommentDto::new).collect(Collectors.toList());
    }

    @Override
    public CommentDto findById(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(()->new CommentNotFoundException("Комментарий с ID=<"+id+"> в базе найден"));
        CommentDto commentDto = new CommentDto(comment);
        return commentDto;
    }

    @Override
    public boolean deleteById(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(()->new CommentNotFoundException("Комментарий с ID=<"+id+"> для удаления найден"));
        commentRepository.delete(comment);
        return true;
    }

    @Override
    public List<CommentDto> findAllCommentsByBookId(Long id) {
        List<Comment> comments = commentRepository.findAllCommentsByBookId(id);
        return comments.stream().map(CommentDto::new).collect(Collectors.toList());
    }

    @Override
    public CommentDto save(CommentDto commentDto) {
        Comment comment = commentMapper.getCommentFromCommentDto(commentDto);
        comment.setId(null);
        Comment newComment = commentRepository.save(comment);
        return commentMapper.getCommentDtoFromComment(newComment);
    }

    @Override
    public CommentDto update(CommentDto commentDto) {
        Comment comment = commentRepository.findById(commentDto.getId()).orElseThrow(()->new CommentNotFoundException("Комментарий с ID=<"+commentDto.getId()+"> для обновления не найден"));
        comment.setText(commentDto.getText());
        comment.setUser(userService.findByUserId(commentDto.getUserId()));
        comment.setUpdated(OffsetDateTime.now());
        return new CommentDto(commentRepository.save(comment));
    }

}


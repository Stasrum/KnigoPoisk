package com.geekbrains.knigopoisk.services.impl;

import com.geekbrains.knigopoisk.dto.CommentDto;
import com.geekbrains.knigopoisk.entities.Comment;
import com.geekbrains.knigopoisk.exceptions.CommentNotFoundException;
import com.geekbrains.knigopoisk.exceptions.UserNotFoundException;
import com.geekbrains.knigopoisk.repositories.CommentRepository;
import com.geekbrains.knigopoisk.repositories.UserRepository;
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


    private CommentRepository commentRepository;

    @Autowired
    public void setCommentRepository(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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
        Comment comment = CommentDto.fromDto(commentDto);
        comment.setId(null);
        return new CommentDto(commentRepository.save(comment));
    }

    @Override
    public CommentDto update(CommentDto commentDto) {
        Comment comment = commentRepository.findById(commentDto.getId()).orElseThrow(()->new CommentNotFoundException("Комментарий с ID=<"+commentDto.getId()+"> для обновления не найден"));
        comment.setText(commentDto.getText());
        comment.setUser(userRepository.findById(commentDto.getUser().getId()).orElseThrow(()-> new UserNotFoundException("Пользователь с ID=<"+commentDto.getUser().getId()+"> для обновления комментария не найден ")));
        comment.setUpdated(OffsetDateTime.now());
        return new CommentDto(commentRepository.save(comment));
    }

}


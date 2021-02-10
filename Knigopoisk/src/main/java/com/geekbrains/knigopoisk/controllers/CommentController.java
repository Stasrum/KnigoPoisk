package com.geekbrains.knigopoisk.controllers;

import com.geekbrains.knigopoisk.controllers.facade.AuthorControllerApi;
import com.geekbrains.knigopoisk.controllers.facade.CommentControllerApi;
import com.geekbrains.knigopoisk.dto.CommentDto;
import com.geekbrains.knigopoisk.entities.Author;
import com.geekbrains.knigopoisk.entities.Book;
import com.geekbrains.knigopoisk.entities.Comment;
import com.geekbrains.knigopoisk.services.contracts.AuthorService;
import com.geekbrains.knigopoisk.services.contracts.CommentService;
import com.geekbrains.knigopoisk.services.impl.CommentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Validated
@RequiredArgsConstructor
@RestController
public class CommentController implements CommentControllerApi {

    private final CommentServiceImpl commentService;

    @Override
    public List<Comment> getAllComments() {
        return commentService.getAll();
    }

    @Override
    public Comment findById(@NotNull Long id) {
        return commentService.findById(id);
    }

    @Override
    public boolean deleteById(@NotNull Long id) {
        commentService.deleteById(id);
        return false;
    }

    @Override
    public Comment createComment(@RequestBody @Valid Comment commentDto) {
        return commentService.save(commentDto);
    }

}

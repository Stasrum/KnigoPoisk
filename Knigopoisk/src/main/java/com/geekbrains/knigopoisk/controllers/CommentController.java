package com.geekbrains.knigopoisk.controllers;

import com.geekbrains.knigopoisk.controllers.facade.CommentControllerApi;
import com.geekbrains.knigopoisk.dto.CommentDto;
import com.geekbrains.knigopoisk.services.impl.CommentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@RequiredArgsConstructor
@RestController
public class CommentController implements CommentControllerApi {

    private final CommentServiceImpl commentService;

    @Override
    public List<CommentDto> getAllComments() {
        return commentService.getAll();
    }

    @Override
    public List<CommentDto> getAllCommentsByBookId(@NotNull Long id) {
        return commentService.findAllCommentsByBookId(id);
    }

    @Override
    public CommentDto findById(@NotNull Long id) {
        return commentService.findById(id);
    }

    @Override
    public CommentDto createComment(@RequestBody @Valid CommentDto commentDto) {
        return commentService.save(commentDto);
    }

    @Override
    public CommentDto updateComment(@RequestBody @Valid CommentDto commentDto) {

        return commentService.update(commentDto);
    }

    @Override
    public boolean deleteById(@PathVariable @NotNull Long id) {
        return commentService.deleteById(id);
    }
}


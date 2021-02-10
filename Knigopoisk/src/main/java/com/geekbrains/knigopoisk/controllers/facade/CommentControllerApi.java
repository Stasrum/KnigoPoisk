package com.geekbrains.knigopoisk.controllers.facade;

import com.geekbrains.knigopoisk.entities.Comment;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface CommentControllerApi {

    @GetMapping(value = "/comments/", produces = "application/json")
    List<Comment> getAllComments();

    @GetMapping(value = "/comments/{id}", produces = "application/json")
    Comment findById(@PathVariable("id") @NotNull Long id);

    @GetMapping(value = "/comments/delete/{id}")
    boolean deleteById(@PathVariable("id") @NotNull Long id);

    @PostMapping(value = "/comments/create", consumes = "application/json", produces = "application/json")
    Comment createComment(@RequestBody Comment comment);
}

package com.geekbrains.knigopoisk.controllers.facade;

import com.geekbrains.knigopoisk.entities.Comment;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("/api/v1/admin/comments")
public interface CommentControllerApi {

    @GetMapping(value = "/", produces = "application/json")
    List<Comment> getAllComments();

    @GetMapping(value = "/{id}", produces = "application/json")
    Comment findById(@PathVariable("id") @NotNull Long id);

    @GetMapping(value = "/delete/{id}")
    boolean deleteById(@PathVariable("id") @NotNull Long id);

    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    Comment createComment(@RequestBody Comment comment);
}

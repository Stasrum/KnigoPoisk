package com.geekbrains.knigopoisk.controllers.facade;

import com.geekbrains.knigopoisk.dto.CommentDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("/api/v1")
public interface CommentControllerApi {

    @GetMapping(value = "/comments/", produces = "application/json")
    List<CommentDto> getAllComments();

    @GetMapping(value = "/comments/{id}", produces = "application/json")
    CommentDto findById(@PathVariable("id") @NotNull Long id);

    @PostMapping(value = "/comments/create", consumes = "application/json", produces = "application/json")
    CommentDto createComment(@RequestBody CommentDto commentDto);

    @PutMapping(value = "/comments/update", consumes = "application/json", produces = "application/json")
    CommentDto updateComment(@RequestBody @Valid CommentDto commentDto);

    @DeleteMapping(value = "/comments/delete/{id}")
    boolean deleteById(@PathVariable("id") @NotNull Long id);

}

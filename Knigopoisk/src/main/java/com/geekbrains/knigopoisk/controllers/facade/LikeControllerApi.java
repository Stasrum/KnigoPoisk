package com.geekbrains.knigopoisk.controllers.facade;

import com.geekbrains.knigopoisk.dto.LikeDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;


@RequestMapping("/api/v1")
public interface LikeControllerApi {

    @GetMapping(value = "/likes", produces = "application/json")
    List<LikeDto> getAll();

    @GetMapping(value = "/likes/book/{id}", produces = "application/json")
    List<LikeDto> getAllByBookId(@PathVariable("id") @NotNull Long id);

    @GetMapping(value = "/likescount/book/{id}",produces = "application/json")
    Integer countLikesByBookId(@PathVariable("id") @NotNull Long id);

    @GetMapping(value = "/likes/{id}", produces = "application/json")
    LikeDto findById(@PathVariable("id") @NotNull Long id);

    @PostMapping(value = "/likes/create", consumes = "application/json", produces = "application/json")
    LikeDto create(@RequestBody @Valid LikeDto likeDto);

    @PutMapping(value = "/likes/update", consumes = "application/json", produces = "application/json")
    LikeDto update(@RequestBody @Valid LikeDto likeDto);

    @DeleteMapping(value = "/likes/delete/{id}")
    boolean deleteById(@PathVariable("id") @NotNull Long id);

}

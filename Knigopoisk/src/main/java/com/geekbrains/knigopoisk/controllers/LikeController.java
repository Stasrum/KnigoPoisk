package com.geekbrains.knigopoisk.controllers;

import com.geekbrains.knigopoisk.controllers.facade.LikeControllerApi;
import com.geekbrains.knigopoisk.dto.LikeDto;
import com.geekbrains.knigopoisk.services.impl.LikeServiceImpl;
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
public class LikeController implements LikeControllerApi {

    private final LikeServiceImpl likeService;

    @Override
    public List<LikeDto> getAll() {
        return likeService.getAll();
    }

    @Override
    public List<LikeDto> getAllByBookId(@NotNull Long id) {
        return likeService.findAllLikesByBookId(id);
    }

    public Integer countLikesByBookId(@NotNull Long id) {
        return likeService.countLikesByBookId(id);
    }


    @Override
    public LikeDto findById(@NotNull Long id) {
        return likeService.findById(id);
    }

    @Override
    public LikeDto create(@RequestBody @Valid LikeDto likeDto) {
        return likeService.save(likeDto);
    }

    @Override
    public LikeDto update(@RequestBody @Valid LikeDto likeDto) {

        return likeService.update(likeDto);
    }

    @Override
    public boolean deleteById(@PathVariable @NotNull Long id) {
        return likeService.deleteById(id);
    }
}


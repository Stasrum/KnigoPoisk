package com.geekbrains.knigopoisk.services.contracts;

import com.geekbrains.knigopoisk.dto.LikeDto;

import java.util.List;

public interface LikeService {
    List<LikeDto> getAll();
    LikeDto findById(Long id);
    LikeDto save(LikeDto likeDto);
    LikeDto update(LikeDto likeDto);
    boolean deleteById(Long id);
    Integer countLikesByBookId(Long id);
    List<LikeDto> findAllLikesByBookId(Long id);
}
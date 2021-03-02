package com.geekbrains.knigopoisk.services.impl;

import com.geekbrains.knigopoisk.dto.LikeDto;
import com.geekbrains.knigopoisk.dto.mappers.LikeMapper;
import com.geekbrains.knigopoisk.entities.Like;
import com.geekbrains.knigopoisk.exceptions.LikeNotFoundException;
import com.geekbrains.knigopoisk.repositories.LikeRepository;
import com.geekbrains.knigopoisk.services.contracts.BookService;
import com.geekbrains.knigopoisk.services.contracts.LikeService;
import com.geekbrains.knigopoisk.services.contracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final UserService userService;
    private final BookService bookService;
    private final LikeMapper likeMapper;

    @Override
    public List<LikeDto> getAll() {
        List<Like> likes = likeRepository.findAll();
        return likes.stream().map(LikeDto::new).collect(Collectors.toList());
    }

    @Override
    public LikeDto findById(Long id) {
        Like like = likeRepository.findById(id).orElseThrow(()->new LikeNotFoundException("Лайк с ID=<"+id+"> в базе найден"));
        LikeDto likeDto = new LikeDto(like);
        return likeDto;
    }

    @Override
    public boolean deleteById(Long id) {
        Like like = likeRepository.findById(id).orElseThrow(()->new LikeNotFoundException("Лайк с ID=<"+id+"> для удаления найден"));
        likeRepository.delete(like);
        return true;
    }

    @Override
    public List<LikeDto> findAllLikesByBookId(Long id) {
        List<Like> likes = likeRepository.findAllLikesByBookId(id);
        return likes.stream().map(LikeDto::new).collect(Collectors.toList());
    }

    @Override
    public Integer countLikesByBookId(Long id) {
        List<Like> likes = likeRepository.findAllLikesByBookId(id);
        return likes.size();
    }

    @Override
    public LikeDto save(LikeDto likeDto) {
        Like like = likeMapper.getLikeFromLikeDto(likeDto);
        like.setId(null);
        Like newLike = likeRepository.save(like);
        return likeMapper.getLikeDtoFromLike(newLike);
    }

    @Override
    public LikeDto update(LikeDto likeDto) {
        Like like = likeRepository.findById(likeDto.getId()).orElseThrow(()->new LikeNotFoundException("Лайк с ID=<"+likeDto.getId()+"> для обновления не найден"));
        like.setBook(bookService.findBookById(likeDto.getBookId()));
        like.setUser(userService.findByUserId(likeDto.getUserId()));
        like.setUpdated(OffsetDateTime.now());
        return new LikeDto(likeRepository.save(like));
    }

}


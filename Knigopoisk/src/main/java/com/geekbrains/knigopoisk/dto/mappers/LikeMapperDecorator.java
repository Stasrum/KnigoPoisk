package com.geekbrains.knigopoisk.dto.mappers;

import com.geekbrains.knigopoisk.dto.BookDto;
import com.geekbrains.knigopoisk.dto.LikeDto;
import com.geekbrains.knigopoisk.entities.Like;
import com.geekbrains.knigopoisk.services.contracts.BookService;
import com.geekbrains.knigopoisk.services.contracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.time.OffsetDateTime;

public abstract class LikeMapperDecorator implements LikeMapper {

    @Autowired
    @Qualifier("delegate")
    private LikeMapper delegate;

    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;

    @Override
    public Like getLikeFromLikeDto(LikeDto likeDto) {
        Like like = delegate.getLikeFromLikeDto(likeDto);
        like.setUser(userService.findByUserId(likeDto.getUserId()));
        like.setBook(BookDto.fromDto(bookService.findById(likeDto.getBookId())));
        like.setCreated(OffsetDateTime.now());
        like.setUpdated(OffsetDateTime.now());
        return like;
    }
}

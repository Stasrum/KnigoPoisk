package com.geekbrains.knigopoisk.dto.mappers;

import com.geekbrains.knigopoisk.dto.LikeDto;
import com.geekbrains.knigopoisk.entities.Like;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
@DecoratedWith(LikeMapperDecorator.class)
public interface LikeMapper {

    Like getLikeFromLikeDto(LikeDto likeDto);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "book.id", target = "bookId")
    LikeDto getLikeDtoFromLike(Like like);

    List<Like> getLikeListFromLikeDtoList(List<LikeDto> LikeDtoList);
    List<LikeDto> getLikeDtoListFromLikeList(List<Like> LikeList);
}

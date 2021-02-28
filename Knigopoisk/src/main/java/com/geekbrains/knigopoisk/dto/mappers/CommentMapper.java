package com.geekbrains.knigopoisk.dto.mappers;

import com.geekbrains.knigopoisk.dto.CommentDto;
import com.geekbrains.knigopoisk.dto.RoleDto;
import com.geekbrains.knigopoisk.entities.Comment;
import com.geekbrains.knigopoisk.entities.Role;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
@DecoratedWith(CommentMapperDecorator.class)
public interface CommentMapper {

    Comment getCommentFromCommentDto(CommentDto commentDto);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.username", target = "userName")
    @Mapping(source = "book.id", target = "bookId")
    CommentDto getCommentDtoFromComment(Comment comment);

    List<Comment> getCommentListFromCommentDtoList(List<CommentDto> CommentDtoList);
    List<CommentDto> getCommentDtoListFromCommentList(List<Comment> CommentList);
}

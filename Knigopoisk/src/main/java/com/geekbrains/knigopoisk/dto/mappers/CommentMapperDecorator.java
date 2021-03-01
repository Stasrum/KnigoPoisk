package com.geekbrains.knigopoisk.dto.mappers;

import com.geekbrains.knigopoisk.dto.BookDto;
import com.geekbrains.knigopoisk.dto.CommentDto;
import com.geekbrains.knigopoisk.entities.Comment;
import com.geekbrains.knigopoisk.services.contracts.BookService;
import com.geekbrains.knigopoisk.services.contracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.time.OffsetDateTime;

public abstract class CommentMapperDecorator implements CommentMapper {

    @Autowired
    @Qualifier("delegate")
    private CommentMapper delegate;

    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;

    @Override
    public Comment getCommentFromCommentDto(CommentDto commentDto) {
        Comment comment = delegate.getCommentFromCommentDto(commentDto);

        comment.setUser(userService.findByUserId(commentDto.getUserId()));
        comment.setBook(BookDto.fromDto(bookService.findById(commentDto.getBookId())));
        comment.setCreated(OffsetDateTime.now());
        comment.setUpdated(OffsetDateTime.now());

        return comment;
    }
}

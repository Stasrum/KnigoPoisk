package com.geekbrains.knigopoisk.dto;


import com.geekbrains.knigopoisk.entities.Comment;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
public class CommentDto {
    private Long id;
    private String text;
    private UserForAdminsEditDto user;
    private OffsetDateTime created;
    private OffsetDateTime updated;

    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.text = comment.getText();
        this.user = new UserForAdminsEditDto(comment.getUser());
        this.created = comment.getCreated();
        this.updated = comment.getUpdated();
    }

    public static Comment fromDto(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setText(commentDto.getText());
        comment.setUser(UserForAdminsEditDto.fromAdminsEditDto(commentDto.getUser()));
        comment.setCreated(commentDto.getCreated());
        comment.setUpdated(commentDto.getUpdated());
        return comment;
    }
}

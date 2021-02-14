package com.geekbrains.knigopoisk.dto;


import com.geekbrains.knigopoisk.entities.Comment;
import com.geekbrains.knigopoisk.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentDto {
    private Long id;
    private String text;
    private User user;

    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.text = comment.getText();
        this.user = comment.getUser();
    }

    public static Comment fromDto(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setText(commentDto.getText());
        comment.setUser(commentDto.getUser());
        return comment;
    }
}

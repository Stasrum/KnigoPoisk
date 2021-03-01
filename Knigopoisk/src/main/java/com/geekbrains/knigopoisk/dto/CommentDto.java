package com.geekbrains.knigopoisk.dto;


import com.geekbrains.knigopoisk.entities.Comment;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentDto {
    private Long id;
    private String text;
    private Long userId;
    private String userName;
    private Long bookId;

    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.text = comment.getText();

        this.userId = comment.getUser().getId();
        this.userName = comment.getUser().getUsername();
        this.bookId = comment.getBook().getId();
    }
}

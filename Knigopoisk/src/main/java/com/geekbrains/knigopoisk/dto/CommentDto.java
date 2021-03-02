package com.geekbrains.knigopoisk.dto;


import com.geekbrains.knigopoisk.entities.Comment;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
public class CommentDto {
    private Long id;
    @NotNull (message = "comment field must be not null")
    private String text;
    @NotNull (message = "userId must be not null")
    private Long userId;
    private String userName;
    @NotNull(message = "bookId must be not null")
    private Long bookId;

    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.text = comment.getText();

        this.userId = comment.getUser().getId();
        this.userName = comment.getUser().getUsername();
        this.bookId = comment.getBook().getId();
    }
}

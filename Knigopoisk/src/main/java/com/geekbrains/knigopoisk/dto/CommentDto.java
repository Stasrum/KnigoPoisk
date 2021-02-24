package com.geekbrains.knigopoisk.dto;


import com.geekbrains.knigopoisk.entities.Comment;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentDto {
    private Long id;
    private String text;
    private UserForAdminsEditDto user;
    private BookDto bookDto;


    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.text = comment.getText();
        this.user = new UserForAdminsEditDto(comment.getUser());
        this.bookDto = new BookDto(comment.getBook());
    }

    public static Comment fromDto(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setText(commentDto.getText());
        comment.setUser(UserForAdminsEditDto.fromAdminsEditDto(commentDto.getUser()));
        comment.setBook(BookDto.fromDto(commentDto.getBookDto()));
        return comment;
    }
}

package com.geekbrains.knigopoisk.dto;


import com.geekbrains.knigopoisk.entities.Like;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LikeDto {
    private Long id;
    private Long userId;
    private Long bookId;

    public LikeDto(Like like) {
        this.id = like.getId();
        this.userId = like.getUser().getId();
        this.bookId = like.getBook().getId();
    }
}

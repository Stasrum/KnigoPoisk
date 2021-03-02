package com.geekbrains.knigopoisk.dto;


import com.geekbrains.knigopoisk.entities.Like;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class LikeDto {
    private Long id;
    @NotNull(message = "userId field must be not null")
    private Long userId;
    @NotNull(message = "bookId field must be not null")
    private Long bookId;

    public LikeDto(Like like) {
        this.id = like.getId();
        this.userId = like.getUser().getId();
        this.bookId = like.getBook().getId();
    }
}

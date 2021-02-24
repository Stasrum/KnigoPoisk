package com.geekbrains.knigopoisk.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "Comment")
@Table(name = "comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Comment extends DefaultEntity{

    @NotNull(message = "comment must be not null")
    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;

}

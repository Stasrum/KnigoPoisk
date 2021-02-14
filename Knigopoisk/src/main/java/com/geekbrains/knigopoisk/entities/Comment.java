package com.geekbrains.knigopoisk.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

}

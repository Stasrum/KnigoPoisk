package com.geekbrains.knigopoisk.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "Like")
@Table(name = "likes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Like extends DefaultEntity{

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;

}

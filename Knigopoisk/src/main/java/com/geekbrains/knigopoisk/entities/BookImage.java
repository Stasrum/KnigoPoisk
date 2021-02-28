package com.geekbrains.knigopoisk.entities;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "book_images")
@EqualsAndHashCode(callSuper = true)
@Data
public class BookImage extends DefaultEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "path")
    private String path;
}

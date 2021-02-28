package com.geekbrains.knigopoisk.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "book_images")
@Data
public class BookImage extends DefaultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "path")
    private String path;
}

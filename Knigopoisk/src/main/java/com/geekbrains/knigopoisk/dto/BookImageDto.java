package com.geekbrains.knigopoisk.dto;

import com.geekbrains.knigopoisk.entities.Book;
import lombok.Data;

@Data
public class BookImageDto {
    private Long id;
    private Book book;
    private String path;


}

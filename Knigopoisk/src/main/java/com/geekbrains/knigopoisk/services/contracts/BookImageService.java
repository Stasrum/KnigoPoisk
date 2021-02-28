package com.geekbrains.knigopoisk.services.contracts;

import com.geekbrains.knigopoisk.entities.Book;
import com.geekbrains.knigopoisk.entities.BookImage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface BookImageService {
    BookImage addBookImage(Book book, MultipartFile multipartFile) throws IOException;
}

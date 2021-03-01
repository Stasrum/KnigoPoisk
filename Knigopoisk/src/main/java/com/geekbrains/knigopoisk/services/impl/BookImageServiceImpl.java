package com.geekbrains.knigopoisk.services.impl;

import com.geekbrains.knigopoisk.entities.Book;
import com.geekbrains.knigopoisk.entities.BookImage;
import com.geekbrains.knigopoisk.repositories.BookImageRepository;
import com.geekbrains.knigopoisk.services.contracts.BookImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookImageServiceImpl implements BookImageService {
    private static final String UPLOADED_FOLDER = "./images/";
    private final BookImageRepository bookImageRepository;

    @Override
    public BookImage addBookImage(Book book, MultipartFile multipartFile) throws IOException {
        String fileName = UUID.randomUUID().toString() + multipartFile.getOriginalFilename();

        Path path = Paths.get(UPLOADED_FOLDER + fileName);
        multipartFile.transferTo(path);

        BookImage bookImage = new BookImage();
        bookImage.setId(null);
        bookImage.setBook(book);
        bookImage.setPath(fileName);

        return bookImageRepository.save(bookImage);
    }
}

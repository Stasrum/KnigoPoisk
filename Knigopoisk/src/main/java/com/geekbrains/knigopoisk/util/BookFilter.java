package com.geekbrains.knigopoisk.util;

import com.geekbrains.knigopoisk.entities.Book;
import com.geekbrains.knigopoisk.repositories.specifications.BookSpecifications;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

@Getter
public class BookFilter {
    private Specification<Book> spec;

    public BookFilter(Map<String, String> params) {
        spec = Specification.where(null);

        String filterTitle = params.get("title");
        if (filterTitle != null && !filterTitle.isBlank()) {
            spec = spec.and(BookSpecifications.titleLike(filterTitle));
        }

        String filterNameAuthor = params.get("nameAuthor");
        if (filterNameAuthor != null && !filterNameAuthor.isBlank()) {
            spec = spec.and(BookSpecifications.nameAuthorLike(filterNameAuthor));
        }

        String filterNameGenre = params.get("nameGenre");
        if (filterNameGenre != null && !filterNameGenre.isBlank()) {
            spec = spec.and(BookSpecifications.nameGenreLike(filterNameGenre));
        }
    }
}

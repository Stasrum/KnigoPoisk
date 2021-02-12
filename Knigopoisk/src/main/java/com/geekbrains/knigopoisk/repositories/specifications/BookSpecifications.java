package com.geekbrains.knigopoisk.repositories.specifications;

import com.geekbrains.knigopoisk.entities.Book;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecifications {
    public static Specification<Book> titleLike(String titlePart) {
        return (Specification<Book>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", titlePart)); // where book.title like %titlePart%
    }

    public static Specification<Book> nameAuthorLike(String nameAuthor) {
        return (Specification<Book>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.joinList("authors").get("name"), String.format("%%%s%%", nameAuthor)); // where book.author.name like %nameAuthor%
    }

    public static Specification<Book> nameGenreLike(String nameGenre) {
        return (Specification<Book>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.joinList("genres").get("name"), String.format("%%%s%%", nameGenre)); // where book.genre.name like %nameGenre%
    }
}

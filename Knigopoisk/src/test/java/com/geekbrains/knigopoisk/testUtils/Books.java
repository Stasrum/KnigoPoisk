package com.geekbrains.knigopoisk.testUtils;

import com.geekbrains.knigopoisk.entities.*;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Books {

    public static final Long BOOK_ID = 1L;
    public static final String
            CREATED = "2007-12-03T10:15:30+01:00",
            UPDATED = "2007-12-04T10:15:30+01:00",
            TITLE = "Одноэтажная Америка",
            AUTHOR_1 = "И. Ильф",
            AUTHOR_2 = "Е. Петров",
            LANGUAGE_1 = "Русский",
            LANGUAGE_2 = "Украинский",
            GENRE_1 = "Документальный",
            PUBLISHER_1 = "МИФ",
            DESCRIPTION = "Веселая книга",
            ISBN = "1234567890123";

    public static final int YEAR = 1928;
    public static final Publisher PUBLISHER = new Publisher(PUBLISHER_1, "");

    public static final List<Author> AUTHORS = Arrays.asList(new Author(AUTHOR_1, ""), new Author(AUTHOR_2, ""));
    public static final List<Language> LANGUAGES = Arrays.asList(new Language(LANGUAGE_1, Collections.emptyList()), new Language(LANGUAGE_2,  Collections.emptyList()));
    public static final List<Genre> GENRES = Collections.singletonList(new Genre(GENRE_1, "", Collections.emptyList()));

    public static Book getBook() {

        Book book = new Book();
        book.setId(BOOK_ID);
        book.setCreated(OffsetDateTime.parse(CREATED));
        book.setUpdated(OffsetDateTime.parse(UPDATED));
        book.setTitle(TITLE);
        book.setAuthors(AUTHORS);
        book.setLanguages(LANGUAGES);
        book.setGenres(GENRES);
        book.setPublisher(PUBLISHER);
        book.setDescription(DESCRIPTION);
        book.setYear(YEAR);
        book.setIsbn(ISBN);

        return book;
    }
}

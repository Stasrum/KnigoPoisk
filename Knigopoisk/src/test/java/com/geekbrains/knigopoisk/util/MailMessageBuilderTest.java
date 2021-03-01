package com.geekbrains.knigopoisk.util;

import com.geekbrains.knigopoisk.dto.BookDto;
import com.geekbrains.knigopoisk.entities.Book;
import com.geekbrains.knigopoisk.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.geekbrains.knigopoisk.testUtils.Books.getBook;
import static com.geekbrains.knigopoisk.testUtils.Users.getUser;

class MailMessageBuilderTest {

    @Test
    @DisplayName("Broadcast mail building is success")
    void buildBroadcastMailSuccessTest() {
        User user = getUser();
        Book book = getBook();
        List<BookDto> books = new ArrayList<>();
        books.add(new BookDto(book));
        books.add(new BookDto(book));
        books.add(new BookDto(book));

        MailMessageBuilder mailMessageBuilder = new MailMessageBuilder();
        String mail = mailMessageBuilder.buildBroadcastMail(user, books);
        Assertions.assertTrue(mail.contains(user.getFirstName()));
        Assertions.assertTrue(mail.contains(MailMessageBuilder.SITE_URL + "/user/unsubscribe"));
        Assertions.assertTrue(mail.contains(book.getTitle()));
        Assertions.assertTrue(mail.contains(book.getDescription()));
    }
}
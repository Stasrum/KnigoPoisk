package com.geekbrains.knigopoisk.services.impl;

import com.geekbrains.knigopoisk.dto.BookDto;
import com.geekbrains.knigopoisk.dto.SubscriptionDto;
import com.geekbrains.knigopoisk.entities.Book;
import com.geekbrains.knigopoisk.entities.User;
import com.geekbrains.knigopoisk.services.contracts.BookService;
import com.geekbrains.knigopoisk.services.contracts.MailService;
import com.geekbrains.knigopoisk.services.contracts.UserService;
import com.geekbrains.knigopoisk.util.MailMessageBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private static final String BROADCAST_TITLE = "Новинки недели";

    private final MailMessageBuilder messageBuilder = new MailMessageBuilder();
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    private final JavaMailSender javaMailSender;
    private final UserService userService;
    private final BookService bookService;


    public void sendLastWeekBooksMail(SubscriptionDto subscriptionDto) {
        List<User> users = subscriptionDto.getUsersId().stream()
                .map(userService::findByUserId)
                .collect(Collectors.toList());
        List<Book> books = subscriptionDto.getBooksId().stream()
                .map(bookId-> BookDto.fromDto(bookService.findById(bookId)))
                .collect(Collectors.toList());
        sendBroadcastMail(users, books);
    }

    public void sendBroadcastMail(List<User> users, List<Book> books) {
        for (User user : users) {
            sendMail(user.getEmail(), BROADCAST_TITLE, messageBuilder.buildBroadcastMail(user, books));
        }
    }

    private void sendMail(String email, String subject, String text) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");

        try {
            helper.setTo(email);
            helper.setText(text, true);
            helper.setSubject(subject);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        try {
            executorService.submit(() -> javaMailSender.send(message));
        } catch (MailException e) {
            e.printStackTrace();
        }
    }
}
package com.geekbrains.knigopoisk.services;

import com.geekbrains.knigopoisk.entities.Book;
import com.geekbrains.knigopoisk.entities.User;
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

@Service
@RequiredArgsConstructor
public class MailService {

    private static final String BROADCAST_TITLE = "Новинки недели";
    private JavaMailSender javaMailSender;
    private MailMessageBuilder messageBuilder;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public void sendMail(String email, String subject, String text) {
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

    public void sendOrderMail(List<User> users, List<Book> books) {
        for (User user : users) {
            sendMail(user.getEmail(), BROADCAST_TITLE, messageBuilder.buildBroadcastMail(user, books));
        }
    }
}
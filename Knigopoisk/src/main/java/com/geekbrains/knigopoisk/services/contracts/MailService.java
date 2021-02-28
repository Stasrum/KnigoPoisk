package com.geekbrains.knigopoisk.services.contracts;

import com.geekbrains.knigopoisk.dto.SubscriptionDto;
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

public interface MailService {
    void sendLastWeekBooksMail(SubscriptionDto subscriptionDto);
    void sendBroadcastMail(List<User> users, List<Book> books);
}
package com.geekbrains.knigopoisk.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.geekbrains.knigopoisk.dto.SubscriptionDto;
import com.geekbrains.knigopoisk.entities.Book;
import com.geekbrains.knigopoisk.entities.User;
import com.geekbrains.knigopoisk.services.impl.MailServiceImpl;
import com.geekbrains.knigopoisk.util.MailMessageBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.internet.MimeMessage;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static com.geekbrains.knigopoisk.testUtils.Users.*;
import static com.geekbrains.knigopoisk.testUtils.Books.*;

class MailServiceTest {

    @Mock
    private JavaMailSender javaMailSender;

    @InjectMocks
    private MailServiceImpl mailService;

    @InjectMocks
    private MailMessageBuilder mailMessageBuilder;

    public MailServiceTest() {
        MockitoAnnotations.openMocks(this);
        when(javaMailSender.createMimeMessage()).thenReturn(new JavaMailSenderImpl().createMimeMessage());
    }

    @Test
    @DisplayName("Sending of broadcast mail is success")
    void sendBroadcastMail() {
        User user = getUser();
        Book book = getBook();
        doAnswer((Answer<String>) invocation -> {
            MimeMessage mimeMessage = (MimeMessage) invocation.getArguments()[0];
            String content = (String) mimeMessage.getContent();
            Assertions.assertTrue(content.contains(user.getFirstName()));
            Assertions.assertTrue(content.contains(book.getTitle()));
            return null;
        }).when(javaMailSender).send(any(MimeMessage.class));
        mailService.sendBroadcastMail(Collections.singletonList(user), Collections.singletonList(book));
    }
}
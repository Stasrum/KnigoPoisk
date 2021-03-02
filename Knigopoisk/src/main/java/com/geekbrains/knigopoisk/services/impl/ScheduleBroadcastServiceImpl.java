package com.geekbrains.knigopoisk.services.impl;

import com.geekbrains.knigopoisk.dto.BookDto;
import com.geekbrains.knigopoisk.dto.UserDetailsDto;
import com.geekbrains.knigopoisk.entities.Book;
import com.geekbrains.knigopoisk.entities.User;
import com.geekbrains.knigopoisk.services.contracts.BookService;
import com.geekbrains.knigopoisk.services.contracts.MailService;
import com.geekbrains.knigopoisk.services.contracts.ScheduleBroadcastService;
import com.geekbrains.knigopoisk.services.contracts.UserService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleBroadcastServiceImpl implements ScheduleBroadcastService {

    private final MailService mailService;
    private final BookService bookService;
    private final UserService userService;

    @Value("${broadcast.schedule.enable:false}")
    private boolean broadcastEnable;

    @Value("${broadcast.age:604800}")
    private String age;

    @Override
    @Scheduled(cron = "${broadcast.schedule.time:0 0 3 * * MON}")
    @Transactional
    public void sendScheduleBroadcastMail() {
        if (broadcastEnable)
            sendBroadcastMail();
    }

    private void sendBroadcastMail(){
        List<User> users = userService.getAll().stream()
                .map(UserDetailsDto::fromDto)
                .collect(Collectors.toList());

        List<BookDto> books = bookService.getAll()
                .stream().filter(bookDto -> {
                    Book book = bookService.findBookById(bookDto.getId());
                    System.out.println(OffsetDateTime.now().toEpochSecond() - (book.getCreated().toEpochSecond()));
                    return OffsetDateTime.now().toEpochSecond() - (book.getCreated().toEpochSecond()) < Long.parseLong(age);
                })
                .collect(Collectors.toList());

        mailService.sendBroadcastMail(users, books);
    }
}

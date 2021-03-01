package com.geekbrains.knigopoisk.services.contracts;

import com.geekbrains.knigopoisk.dto.BookDto;
import com.geekbrains.knigopoisk.dto.SubscriptionDto;
import com.geekbrains.knigopoisk.entities.User;

import java.util.List;

public interface MailService {
    void sendLastWeekBooksMail(SubscriptionDto subscriptionDto);
    void sendBroadcastMail(List<User> users, List<BookDto> books);
}
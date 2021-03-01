package com.geekbrains.knigopoisk.services.impl;

import com.geekbrains.knigopoisk.services.contracts.ScheduleBroadcastService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduleBroadcastServiceImpl implements ScheduleBroadcastService {

    @Value("${broadcast.schedule.enable}")
    boolean broadcastEnable;

    @Override
    @Scheduled(cron = "${broadcast.schedule.time}")
    public void sendScheduleBroadcastMail() {
        if (broadcastEnable)
            System.out.println("It works");
    }
}

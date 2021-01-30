package com.geekbrains.knigopoisk.controllers;

import com.geekbrains.knigopoisk.controllers.facade.PublisherControllerApi;
import com.geekbrains.knigopoisk.entities.Publisher;
import com.geekbrains.knigopoisk.services.contracts.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PublisherController implements PublisherControllerApi {
    private final PublisherService publisherService;

    @Override
    public List<Publisher> getAllPublishers() {
        return publisherService.getAll();
    }

    @Override
    public Publisher createPublisher(@RequestBody Publisher publisher) {
        return publisherService.save(publisher);
    }
}

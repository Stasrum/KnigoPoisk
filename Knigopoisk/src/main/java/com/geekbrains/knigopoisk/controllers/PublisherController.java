package com.geekbrains.knigopoisk.controllers;

import com.geekbrains.knigopoisk.entities.Publisher;
import com.geekbrains.knigopoisk.services.impl.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class PublisherController {
    private final PublisherService publisherService;

    @GetMapping(value = "/publishers", produces = "application/json")
    public List<Publisher> getAllPublishers() {
        return publisherService.getAll();
    }

    @PostMapping(path="/publisher/add", consumes = "application/json", produces = "application/json")
    public Publisher createPublisher(@RequestBody Publisher publisher) {

        return publisherService.save(publisher);
    }
}

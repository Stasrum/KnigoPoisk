package com.geekbrains.knigopoisk.controllers.facade;

import com.geekbrains.knigopoisk.entities.Publisher;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface PublisherControllerApi {

    @GetMapping(value = "/publishers", produces = "application/json")
    List<Publisher> getAllPublishers();

    @PostMapping(value ="/publisher/create", consumes = "application/json", produces = "application/json")
    Publisher createPublisher(@RequestBody @Valid Publisher publisher);
}

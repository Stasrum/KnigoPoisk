package com.geekbrains.knigopoisk.controllers.facade;

import com.geekbrains.knigopoisk.entities.Publisher;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public interface PublisherControllerApi {

    @GetMapping(value = "/publishers", produces = "application/json")
    List<Publisher> getAllPublishers();

    @PostMapping(value ="/publisher/add", consumes = "application/json", produces = "application/json")
    Publisher createPublisher(@RequestBody Publisher publisher);
}

package com.geekbrains.knigopoisk.controllers.facade;

import com.geekbrains.knigopoisk.dto.PublisherDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RequestMapping("/api/v1")
public interface PublisherControllerApi {

    @GetMapping(value = "/publishers", produces = "application/json")
    List<PublisherDto> getAllPublishers();

    @PostMapping(value ="/publisher/create", consumes = "application/json", produces = "application/json")
    PublisherDto createPublisher(@RequestBody @Valid PublisherDto publisher);

    @PutMapping(value = "/publisher/update", consumes = "application/json", produces = "application/json")
    PublisherDto updatePublisher(@RequestBody @Valid PublisherDto publisherDto);

    @DeleteMapping("/publisher/{id}")
    boolean deletePublisherById(@PathVariable Long id);
}

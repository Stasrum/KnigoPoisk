package com.geekbrains.knigopoisk.controllers;

import com.geekbrains.knigopoisk.controllers.facade.PublisherControllerApi;
import com.geekbrains.knigopoisk.dto.PublisherDto;
import com.geekbrains.knigopoisk.entities.Publisher;
import com.geekbrains.knigopoisk.services.contracts.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RequiredArgsConstructor
@RestController
public class PublisherController implements PublisherControllerApi {
    private final PublisherService publisherService;

    @Override
    public List<PublisherDto> getAllPublishers() {
        return publisherService.getAll();
    }

    @Override
    public PublisherDto createPublisher(@RequestBody @Valid PublisherDto publisher) {
        return publisherService.save(publisher);
    }
}

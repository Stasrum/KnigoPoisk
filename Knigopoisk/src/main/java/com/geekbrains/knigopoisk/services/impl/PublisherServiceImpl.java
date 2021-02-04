package com.geekbrains.knigopoisk.services.impl;

import com.geekbrains.knigopoisk.entities.Publisher;
import com.geekbrains.knigopoisk.repositories.PublisherRepository;
import com.geekbrains.knigopoisk.services.contracts.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository publisherRepository;

    @Override
    public List<Publisher> getAll() {
        return publisherRepository.findAll();
    }

    @Override
    public Publisher save(Publisher publisher) {
        publisher.setId(null);
        return publisherRepository.save(publisher);
    }

    @Override
    public Publisher update(Publisher publisher) {
        publisher.setUpdated(OffsetDateTime.now());
        return publisherRepository.save(publisher);
    }
}

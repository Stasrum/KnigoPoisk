package com.geekbrains.knigopoisk.services;

import com.geekbrains.knigopoisk.entities.Publisher;
import com.geekbrains.knigopoisk.repositories.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublisherService {
    private final PublisherRepository publisherRepository;

    public List<Publisher> getAll() {
        return publisherRepository.findAll();
    }

    public Publisher save(Publisher publisher) {
        return publisherRepository.save(publisher);
    }
}

package com.geekbrains.knigopoisk.services.impl;

import com.geekbrains.knigopoisk.dto.PublisherDto;
import com.geekbrains.knigopoisk.entities.Publisher;
import com.geekbrains.knigopoisk.exceptions.PublisherNotFoundException;
import com.geekbrains.knigopoisk.repositories.PublisherRepository;
import com.geekbrains.knigopoisk.services.contracts.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository publisherRepository;

    @Override
    public List<PublisherDto> getAll() {
        List<Publisher> publishers = publisherRepository.findAllByOrderByNameAsc();
        return publishers.stream().map(PublisherDto::new).collect(Collectors.toList());
    }

    @Override
    public PublisherDto save(PublisherDto publisherDto) {
        Publisher p = PublisherDto.fromDto(publisherDto);
        p.setId(null);
        Publisher publisher = publisherRepository.save(p);
        return new PublisherDto(publisher);
    }

    @Override
    public PublisherDto update(PublisherDto publisherDto) {
        Publisher p = publisherRepository.findOneByName(publisherDto.getName()).orElseThrow(()-> new PublisherNotFoundException("Publisher isn't found"));
        p.setUpdated(OffsetDateTime.now());
        Publisher publisher = publisherRepository.save(p);
        return new PublisherDto(publisher);
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<Publisher> publisher = publisherRepository.findById(id);
        if (!publisher.isPresent()) {
            throw new PublisherNotFoundException("Издательство с id = " + id + " не существует");
        }
        publisherRepository.delete(publisher.get());
        return true;
    }
}

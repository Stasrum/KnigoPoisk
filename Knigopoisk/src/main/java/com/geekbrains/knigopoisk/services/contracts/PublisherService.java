package com.geekbrains.knigopoisk.services.contracts;

import com.geekbrains.knigopoisk.dto.PublisherDto;

import java.util.List;

public interface PublisherService {

    List<PublisherDto> getAll();

    PublisherDto save(PublisherDto publisher);

    PublisherDto update(PublisherDto publisher);

    boolean deleteById(Long id);
}

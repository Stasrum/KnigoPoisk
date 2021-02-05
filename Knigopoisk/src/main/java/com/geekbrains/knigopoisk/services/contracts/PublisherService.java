package com.geekbrains.knigopoisk.services.contracts;

import com.geekbrains.knigopoisk.entities.Publisher;

import java.util.List;

public interface PublisherService {

    List<Publisher> getAll();

    Publisher save(Publisher publisher);

    Publisher update(Publisher publisher);
}

package com.geekbrains.knigopoisk.dto;

import com.geekbrains.knigopoisk.entities.Publisher;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PublisherDto {
    private Long id;
    private String name;
    private String description;

    public PublisherDto(Publisher publisher) {
        this.id = publisher.getId();
        this.name = publisher.getName();
        this.description = publisher.getDescription();
    }

    public static Publisher fromDto(PublisherDto publisherDto) {
        Publisher publisher = new Publisher();
        publisher.setId(publisherDto.getId());
        publisher.setName(publisherDto.getName());
        publisher.setDescription(publisherDto.getDescription());
        return publisher;
    }
}

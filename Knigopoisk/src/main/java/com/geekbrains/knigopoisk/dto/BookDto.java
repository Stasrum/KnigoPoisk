package com.geekbrains.knigopoisk.dto;

import com.geekbrains.knigopoisk.entities.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class BookDto {
    private Long id;
    private List<AuthorDto> authors;
    private int year;
    private List<LanguageDto> languages;
    private List<GenreDto> genres;
    private PublisherDto publisher;
    private String description;

    @NotNull(message = "title must be not null")
    @Size(min = 4, max = 255, message = "4 - 255 symbols")
    private String title;

    @Size(min = 13, max = 13, message = "13 symbols")
    private String isbn;

    public BookDto(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();

        List<AuthorDto> aDto = new ArrayList<>();
        for(Author a: book.getAuthors()){
            aDto.add(new AuthorDto(a));
        }
        this.authors = aDto;

        this.year = book.getYear();

        List<LanguageDto> lDto = new ArrayList<>();
        for(Language l: book.getLanguages()){
            lDto.add(new LanguageDto(l));
        }
        this.languages = lDto;

        List<GenreDto> gDto = new ArrayList<>();
        for(Genre g: book.getGenres()){
            gDto.add(new GenreDto(g));
        }
        this.genres = gDto;

        PublisherDto publisherDto = new PublisherDto();
        publisherDto.setId(book.getPublisher().getId());
        publisherDto.setName(book.getPublisher().getName());
        publisherDto.setDescription(book.getPublisher().getDescription());
        this.publisher = publisherDto;
        this.description = book.getDescription();
        this.isbn = book.getIsbn();
    }
}

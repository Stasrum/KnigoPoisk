package com.geekbrains.knigopoisk.repositories;

import com.geekbrains.knigopoisk.entities.Book;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {
}

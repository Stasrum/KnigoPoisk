package com.geekbrains.knigopoisk.repositories;

import com.geekbrains.knigopoisk.entities.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRerository extends PagingAndSortingRepository<User, Long> {
    User findUserByusername(String userName);
}

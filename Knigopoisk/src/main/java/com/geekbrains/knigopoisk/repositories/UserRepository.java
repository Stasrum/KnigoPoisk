package com.geekbrains.knigopoisk.repositories;

import com.geekbrains.knigopoisk.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUsername(String username);

}

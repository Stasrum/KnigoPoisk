package com.geekbrains.knigopoisk.repositories;

import com.geekbrains.knigopoisk.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    Optional<User> findUserByUsername(String username);

    User deleteUserByUsername(String userName);
}

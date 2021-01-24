package com.geekbrains.knigopoisk.repositories;

import com.geekbrains.knigopoisk.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findOneByName(String name);
  Role findByName(String name);
}

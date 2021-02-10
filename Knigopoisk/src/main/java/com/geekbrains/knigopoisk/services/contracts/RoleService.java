package com.geekbrains.knigopoisk.services.contracts;

import com.geekbrains.knigopoisk.entities.Role;

import java.util.List;

public interface RoleService {
    Role getRoleByName(String name);
    List<Role> getAll();
}

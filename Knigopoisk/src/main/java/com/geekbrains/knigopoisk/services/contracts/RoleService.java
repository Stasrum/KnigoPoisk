package com.geekbrains.knigopoisk.services.contracts;

import com.geekbrains.knigopoisk.entities.Role;

public interface RoleService {
    Role getRoleByName(String name);
}

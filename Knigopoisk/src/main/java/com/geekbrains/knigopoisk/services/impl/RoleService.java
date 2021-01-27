package com.geekbrains.knigopoisk.services.impl;

import com.geekbrains.knigopoisk.entities.Role;
import com.geekbrains.knigopoisk.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role getRoleByName(String name){
        return roleRepository.findOneByName(name);
    }
}

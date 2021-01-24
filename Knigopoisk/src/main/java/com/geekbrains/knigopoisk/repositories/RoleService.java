package com.geekbrains.knigopoisk.repositories;

import com.geekbrains.knigopoisk.entities.Role;
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

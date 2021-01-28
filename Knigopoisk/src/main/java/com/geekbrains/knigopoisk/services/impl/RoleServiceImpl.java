package com.geekbrains.knigopoisk.services.impl;

import com.geekbrains.knigopoisk.entities.Role;
import com.geekbrains.knigopoisk.repositories.RoleRepository;
import com.geekbrains.knigopoisk.services.contracts.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role getRoleByName(String name){
        return roleRepository.findOneByName(name);
    }
}

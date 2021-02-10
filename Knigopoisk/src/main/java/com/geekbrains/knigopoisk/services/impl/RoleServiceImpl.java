package com.geekbrains.knigopoisk.services.impl;

import com.geekbrains.knigopoisk.entities.Role;
import com.geekbrains.knigopoisk.repositories.RoleRepository;
import com.geekbrains.knigopoisk.services.contracts.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role getRoleByName(String name){
        return roleRepository.findOneByName(name);
    }
}

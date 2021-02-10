package com.geekbrains.knigopoisk.services.impl;

import com.geekbrains.knigopoisk.entities.Role;
import com.geekbrains.knigopoisk.exceptions.RoleNotFoundException;
import com.geekbrains.knigopoisk.repositories.RoleRepository;
import com.geekbrains.knigopoisk.services.contracts.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.findRoleByName(name).orElseThrow(() ->
                new RoleNotFoundException("Role '" + name + "' not found"));
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }
}

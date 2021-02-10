package com.geekbrains.knigopoisk.dto.mappers;

import com.geekbrains.knigopoisk.dto.UserRegistrationDto;
import com.geekbrains.knigopoisk.entities.User;
import com.geekbrains.knigopoisk.services.contracts.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;

@RequiredArgsConstructor
public abstract class UserMapperDecorator implements UserMapper {

    @Qualifier("delegate")
    private final UserMapper delegate;

    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Override
    public User getUserFromUserRegistrationDto(UserRegistrationDto userRegistrationDto) {
        User user = delegate.getUserFromUserRegistrationDto(userRegistrationDto);
        user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
        user.setEnabled(true);
        user.setAccountNotExpired(true);
        user.setAccountNotLocked(true);
        user.setCredentialsNotExpired(true);
        user.setRoles(Collections.singletonList(roleService.getRoleByName("USER")));
        return user;
    }
}

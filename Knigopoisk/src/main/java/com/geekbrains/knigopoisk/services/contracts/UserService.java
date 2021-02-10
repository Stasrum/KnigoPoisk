package com.geekbrains.knigopoisk.services.contracts;

import com.geekbrains.knigopoisk.dto.UserDetailsDto;
import com.geekbrains.knigopoisk.dto.UserPasswordDto;
import com.geekbrains.knigopoisk.dto.UserRegistrationDto;
import com.geekbrains.knigopoisk.entities.Role;
import com.geekbrains.knigopoisk.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService extends UserDetailsService {
    @Transactional
    User findByUserId(Long userId);

    @Transactional
    User findByUserName(String userName);

    @Transactional
    User save(UserRegistrationDto userRegistrationDto);

    @Transactional
    boolean updateUserDetailsFromUserDetailsDto(UserDetailsDto userDetailsDto);

    @Transactional
    boolean updateUserPasswordFromUserPasswordDto(Long userId, UserPasswordDto userPasswordDto);

    @Transactional
    boolean save(User user);

    @Transactional
    boolean deleteByUserName(String userName);

    @Transactional
    boolean deleteByUserId(Long id);

    @Transactional
    List<User> getAll();

    @Transactional
    boolean isUserByNameExists(String userName);

    @Transactional
    List<Role> getAssignedRolesByUserId(Long userId);

    @Transactional
    List<Role> getUnAssignedRolesByUserId(Long userId);

    @Transactional
    List<Role> removeRoleByRoleName(Long userId, String roleName);

    @Transactional
    List<Role> addRoleByRoleName(Long userId, String roleName);
}

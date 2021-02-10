package com.geekbrains.knigopoisk.services.impl;


import com.geekbrains.knigopoisk.dto.UserDetailsDto;
import com.geekbrains.knigopoisk.dto.UserPasswordDto;
import com.geekbrains.knigopoisk.dto.UserRegistrationDto;
import com.geekbrains.knigopoisk.dto.mappers.UserMapper;
import com.geekbrains.knigopoisk.entities.Role;
import com.geekbrains.knigopoisk.entities.User;
import com.geekbrains.knigopoisk.exceptions.RoleAlreadyExistsException;
import com.geekbrains.knigopoisk.exceptions.UserNotFoundException;
import com.geekbrains.knigopoisk.repositories.UserRepository;
import com.geekbrains.knigopoisk.services.contracts.RoleService;
import com.geekbrains.knigopoisk.services.contracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Неверное имя пользователя или пароль."));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    public User findByUserId(Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new UserNotFoundException("User id = <" + userId + "> not found"));
    }

    @Override
    @Transactional
    public User findByUserName(String userName) {
        return userRepository.findUserByUsername(userName).orElseThrow(() ->
                new UserNotFoundException("User name = <" + userName + "> not found"));
    }

    @Override
    @Transactional
    public User save(UserRegistrationDto userRegistrationDto) {
        User user = userMapper.getUserFromUserRegistrationDto(userRegistrationDto);
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public boolean updateUserDetailsFromUserDetailsDto(UserDetailsDto userDetailsDto) {
        User user = userRepository.findById(userDetailsDto.getId()).orElseThrow(() ->
                new UserNotFoundException("User id = <" + userDetailsDto.getId() + "> name = <" + userDetailsDto.getUserName() + "> not found"));
        userMapper.updateUserFromUserDetailsDto(userDetailsDto, user);
        userRepository.save(user);
        return true;
    }

    @Override
    @Transactional
    public boolean updateUserPasswordFromUserPasswordDto(Long userId, UserPasswordDto userPasswordDto) {
        User user = getUserWithExistenceCheck(userId);
        user.setPassword(passwordEncoder.encode(userPasswordDto.getPassword()));
        return save(user);
    }

    @Override
    @Transactional
    public boolean save(User user) {
        userRepository.save(user);
        return true;
    }

    @Override
    @Transactional
    public boolean deleteByUserName(String userName) {
        userRepository.deleteUserByUsername(userName);
        return true;
    }

    @Override
    @Transactional
    public boolean deleteByUserId(Long id) {
        userRepository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public boolean isUserByNameExists(String userName) {
        return userRepository.findUserByUsername(userName).isPresent();
    }

    @Override
    @Transactional
    public List<Role> getAssignedRolesByUserId(Long userId) {
        User user = getUserWithExistenceCheck(userId);
        return new ArrayList<>(user.getRoles());
    }

    @Override
    @Transactional
    public List<Role> getUnAssignedRolesByUserId(Long userId) {
        User user = getUserWithExistenceCheck(userId);
        return roleService.getAll().stream()
                .filter(role -> user.getRoles().stream().noneMatch(userRole -> userRole.equals(role)))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<Role> removeRoleByRoleName(Long userId, String roleName) {
        User user = getUserWithExistenceCheck(userId);
        Role role = roleService.getRoleByName(roleName);
        user.getRoles().remove(role);
        return new ArrayList<>(user.getRoles());
    }

    @Override
    @Transactional
    public List<Role> addRoleByRoleName(Long userId, String roleName) {
        User user = getUserWithExistenceCheck(userId);
        Role role = roleService.getRoleByName(roleName);
        if (user.getRoles().stream().anyMatch(currentRole -> currentRole.equals(role))) {
            throw new RoleAlreadyExistsException("Данная роль уже назначена");
        }
        user.getRoles().add(role);
        return new ArrayList<>(user.getRoles());
    }

    private User getUserWithExistenceCheck(Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new UserNotFoundException("User id = <" + userId + "> not found"));
    }
}

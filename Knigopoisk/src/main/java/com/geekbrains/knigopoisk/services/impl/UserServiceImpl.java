package com.geekbrains.knigopoisk.services.impl;


import com.geekbrains.knigopoisk.dto.UserDetailsDto;
import com.geekbrains.knigopoisk.dto.UserPasswordDto;
import com.geekbrains.knigopoisk.dto.UserRegistrationDto;
import com.geekbrains.knigopoisk.dto.mappers.UserMapper;
import com.geekbrains.knigopoisk.entities.Role;
import com.geekbrains.knigopoisk.entities.User;
import com.geekbrains.knigopoisk.exceptions.UserNotFoundException;
import com.geekbrains.knigopoisk.repositories.UserRepository;
import com.geekbrains.knigopoisk.services.contracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

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
    public boolean updateUserDetailsFromUserDetailsDto(Long userId, UserDetailsDto userDetailsDto) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new UserNotFoundException("User id = <" + userDetailsDto.getId() + "> name = <" + userDetailsDto.getUserName() + "> not found"));
        userMapper.updateUserFromUserDetailsDto(userDetailsDto, user);
        userRepository.save(user);

        return true;
    }

    @Override
    public boolean updateUserPasswordFromUserPasswordDto(Long userId, UserPasswordDto userPasswordDto) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new UserNotFoundException("User id = <" + userId + "> not found"));
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
    public boolean isUserByNameExists(String userName) {
        return userRepository.findUserByUsername(userName).isPresent();
    }
}

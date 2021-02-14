package com.geekbrains.knigopoisk.testUtils;

import com.geekbrains.knigopoisk.dto.RoleDto;
import com.geekbrains.knigopoisk.dto.UserDetailsDto;
import com.geekbrains.knigopoisk.dto.UserPasswordDto;
import com.geekbrains.knigopoisk.dto.UserRegistrationDto;

public class TestDtoModels {
    public UserRegistrationDto getUserRegistrationDto() {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        userRegistrationDto.setUserName("testUser");
        userRegistrationDto.setFirstName("testUserFirstName");
        userRegistrationDto.setLastName("testUserLastName");
        userRegistrationDto.setEmail("email@mail.ru");
        userRegistrationDto.setPassword("Qwerty123!");
        userRegistrationDto.setMatchingPassword("Qwerty123!");
        userRegistrationDto.setBirthDay("2000-01-01");

        return userRegistrationDto;
    }

    public UserDetailsDto getUserDetailsDto() {
        UserDetailsDto userDetailsDto = new UserDetailsDto();
        userDetailsDto.setId(1L);
        userDetailsDto.setUserName("newName");
        userDetailsDto.setFirstName("newFirstName");
        userDetailsDto.setLastName("newLastName");
        userDetailsDto.setEmail("new-email@mail.com");
        userDetailsDto.setBirthDay("2005-01-01");

        return userDetailsDto;
    }

    public UserPasswordDto getUserPasswordDto() {
        UserPasswordDto userPasswordDto = new UserPasswordDto();
        userPasswordDto.setPassword("Qwerty123!");
        userPasswordDto.setMatchingPassword("Qwerty123!");

        return userPasswordDto;
    }

    public RoleDto getRoleDto(String roleName) {
        RoleDto roleDto = new RoleDto();
        roleDto.setName(roleName);

        return roleDto;
    }
}

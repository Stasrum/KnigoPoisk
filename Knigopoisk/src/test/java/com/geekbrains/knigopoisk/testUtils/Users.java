package com.geekbrains.knigopoisk.testUtils;


import com.geekbrains.knigopoisk.entities.Role;
import com.geekbrains.knigopoisk.entities.User;

import java.time.LocalDate;
import java.util.Collections;

import static com.geekbrains.knigopoisk.configs.Roles.ROLE_ADMIN;
import static com.geekbrains.knigopoisk.configs.Roles.ROLE_USER;

public class Users {

    public static final String
            ADMIN_USERNAME = "admin",
            ADMIN_PASSWORD_ENCODED = "$2a$10$ts2FXg1jVvuuEIJqIItTB.Ra1ZklHcSYyrnt3AkGVJkekFUjWcu9K",
            ADMIN_PASSWORD_DECODED = "1234",
            USER_USERNAME = "user",
            USER_PASSWORD_ENCODED = "$2a$10$ts2FXg1jVvuuEIJqIItTB.Ra1ZklHcSYyrnt3AkGVJkekFUjWcu9K",
            USER_PASSWORD_DECODED = "1234",
            BAD_USERNAME = "baduser",
            BAD_PASSWORD = "12345";

    public static User getAdmin(){

        Role role = getRoleAdmin();

        User admin = new User();
        admin.setId(1L);
        admin.setUsername(ADMIN_USERNAME);
        admin.setPassword(ADMIN_PASSWORD_ENCODED);
        admin.setRoles(Collections.singletonList(role));
        admin.setEnabled(true);
        admin.setAccountNotLocked(true);
        admin.setAccountNotExpired(true);
        admin.setCredentialsNotExpired(true);
        admin.setFirstName("Петр");
        admin.setLastName("Петров");
        admin.setBirthDay(LocalDate.now().minusYears(18L));
        admin.setEmail("admin@mail.com");

        return admin;
    }

    public static User getUser(){
        Role role = getRoleUser();

        User user = new User();
        user.setId(2L);
        user.setUsername(USER_USERNAME);
        user.setPassword(USER_PASSWORD_ENCODED);
        user.setRoles(Collections.singletonList(role));
        user.setEnabled(true);
        user.setAccountNotLocked(true);
        user.setAccountNotExpired(true);
        user.setCredentialsNotExpired(true);
        user.setFirstName("Ivan");
        user.setLastName("Ivanov");
        user.setBirthDay(LocalDate.now().minusYears(35L));
        user.setEmail("user@mail.com");

        return user;
    }

    public static User getNewUser(){

        Role role = getRoleUser();

        User user = new User();
        user.setId(2L);
        user.setUsername(USER_USERNAME);
        user.setPassword(USER_PASSWORD_ENCODED);
        user.setRoles(Collections.singletonList(role));
        user.setEnabled(true);
        user.setAccountNotLocked(true);
        user.setAccountNotExpired(true);
        user.setCredentialsNotExpired(true);
        user.setFirstName("");
        user.setLastName("");
        user.setBirthDay(null);
        user.setEmail(null);

        return user;
    }

    public static Role getRoleAdmin(){
        Role role = new Role();
        role.setId(1L);
        role.setName(ROLE_ADMIN.name());
        return role;
    }

    public static Role getRoleUser(){
        Role role = new Role();
        role.setId(2L);
        role.setName(ROLE_USER.name());
        return role;
    }
}

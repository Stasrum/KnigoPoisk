package com.geekbrains.knigopoisk.dto;

import com.geekbrains.knigopoisk.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class UserForAdminsEditDto {
    // Поля, которые не может изменять администратор
    // Fields which admin can't change
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthDay;

    // Поля, которые администратор может изменить
    // Fields which admin can change
    private Boolean enabled;
    private Boolean accountNotExpired;
    private Boolean credentialsNotExpired;
    private Boolean accountNotLocked;
    private Collection<RoleDto> roles;

    public UserForAdminsEditDto(User user){

        this.id = user.getId();
        this.userName = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.birthDay = user.getBirthDay();
        this.enabled = user.isEnabled();
        this.accountNotExpired = user.isAccountNotExpired();
        this.credentialsNotExpired = user.isCredentialsNotExpired();
        this.accountNotLocked = user.isAccountNotLocked();
        this.roles = user.getRoles().stream().map(RoleDto::new).collect(Collectors.toList());

    }

    public static User fromAdminsEditDto(UserForAdminsEditDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUserName());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setBirthDay(userDto.getBirthDay());
        user.setEnabled(userDto.getEnabled());
        user.setAccountNotExpired(userDto.getAccountNotExpired());
        user.setCredentialsNotExpired(userDto.getCredentialsNotExpired());
        user.setAccountNotLocked(userDto.accountNotLocked);
        user.setRoles(userDto.getRoles().stream().map(RoleDto::fromDto).collect(Collectors.toList()));
        return user;
    }
}
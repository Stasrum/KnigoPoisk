package com.geekbrains.knigopoisk.dto;

import com.geekbrains.knigopoisk.entities.Role;
import com.geekbrains.knigopoisk.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
public class UserForAdminsEditDto {
    private Long id;
    private Boolean enabled;
    private Boolean accountNotExpired;
    private Boolean credentialsNotExpired;
    private Boolean accountNotLocked;
    private Collection<Role> roles;

    public static UserForAdminsEditDto toUserForAdminsEditDto(User user){
        UserForAdminsEditDto userForAdminsEditDto = new UserForAdminsEditDto();
        userForAdminsEditDto.setId(user.getId());
        userForAdminsEditDto.setEnabled(user.getEnabled());
        userForAdminsEditDto.setAccountNotExpired(user.getAccountNotExpired());
        userForAdminsEditDto.setCredentialsNotExpired(user.getCredentialsNotExpired());
        userForAdminsEditDto.setAccountNotLocked(user.getAccountNotLocked());
        userForAdminsEditDto.setRoles(user.getRoles());
        return userForAdminsEditDto;
    }
}
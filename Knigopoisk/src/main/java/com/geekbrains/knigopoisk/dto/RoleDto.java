package com.geekbrains.knigopoisk.dto;

import com.geekbrains.knigopoisk.entities.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class RoleDto {
    private Long id;
    private String name;

    public RoleDto(Role role){
        this.id = role.getId();
        this.name = role.getName();
    }

    public static Role fromDto(RoleDto roleDto){
        Role role = new Role();
        role.setId(roleDto.getId());
        role.setName(roleDto.getName());
        return role;
    }
}

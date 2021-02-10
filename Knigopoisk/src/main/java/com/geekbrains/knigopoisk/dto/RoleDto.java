package com.geekbrains.knigopoisk.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RoleDto {
    @NotNull(message = "Role name must be not null")
    @Size(min = 4, max = 45)
    private String name;
}

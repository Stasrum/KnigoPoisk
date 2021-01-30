package com.geekbrains.knigopoisk.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity(name = "Role")
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Role extends DafaultEntity{

    @NotNull(message = "Role name must be not null")
    @Column(name = "name")
    private String name;

    // используется в тестах JWT
    public Role(Long id, String name) {
        super(id);
        this.name = name;
    }
}

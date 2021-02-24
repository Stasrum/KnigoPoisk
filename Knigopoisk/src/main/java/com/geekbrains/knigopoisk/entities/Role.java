package com.geekbrains.knigopoisk.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "Role")
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Role extends DefaultEntity {

    @NotNull(message = "Role name must be not null")
    @Column(name = "name")
    private String name;

}

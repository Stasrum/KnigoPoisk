package com.geekbrains.knigopoisk.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Role extends DafaultEntity{

    @Column(name = "name")
    private String name;

    public Role(Long id, String name) {
        super(id);
        this.name = name;
    }
}

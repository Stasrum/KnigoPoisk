package com.geekbrains.knigopoisk.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity(name = "Language")
@Table(name = "languages")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Language extends DafaultEntity{

    @NotNull(message = "Language name must be not null")
    @Column(name="name")
    private String name;
}
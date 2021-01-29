package com.geekbrains.knigopoisk.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "Language")
@Table(name = "languages")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Language extends DafaultEntity{

    @Column(name="name")
    private String name;
}
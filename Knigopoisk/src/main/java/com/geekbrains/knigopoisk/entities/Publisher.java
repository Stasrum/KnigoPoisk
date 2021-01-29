package com.geekbrains.knigopoisk.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "Publisher")
@Table(name = "publishers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Publisher extends DafaultEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

}
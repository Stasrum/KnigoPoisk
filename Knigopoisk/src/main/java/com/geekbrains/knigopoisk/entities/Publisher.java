package com.geekbrains.knigopoisk.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity(name = "Publisher")
@Table(name = "publishers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Publisher extends DefaultEntity {

    @NotNull(message = "Publisher name must be not null")
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
}
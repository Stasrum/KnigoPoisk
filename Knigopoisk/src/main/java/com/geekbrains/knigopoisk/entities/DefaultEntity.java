package com.geekbrains.knigopoisk.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Data
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class DefaultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "created")
    private OffsetDateTime created;

    @UpdateTimestamp
    @Column(name = "updated")
    private OffsetDateTime updated;
}

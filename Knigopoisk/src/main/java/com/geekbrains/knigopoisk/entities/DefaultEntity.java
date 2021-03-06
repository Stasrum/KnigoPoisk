package com.geekbrains.knigopoisk.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Objects;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private OffsetDateTime created;

    @UpdateTimestamp
    @Column(name = "updated")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private OffsetDateTime updated;
}

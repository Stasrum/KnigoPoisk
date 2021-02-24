package com.geekbrains.knigopoisk.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "User")
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User extends DefaultEntity {

    @NotNull(message = "Username must be not null")
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birth_day")
    private LocalDate birthDay;

    @Column(name = "email")
    private String email;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "account_non_expired")
    private boolean accountNotExpired;

    @Column(name = "credentials_non_expired")
    private boolean credentialsNotExpired;

    @Column(name = "account_non_locked")
    private boolean accountNotLocked;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;


}

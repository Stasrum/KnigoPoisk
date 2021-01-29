package com.geekbrains.knigopoisk.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;

@Entity(name = "User")
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User extends DafaultEntity{

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "age")
    private Integer age;

    @Column(name = "email")
    private String email;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "account_non_expired")
    private Boolean accountNotExpired;

    @Column(name = "credentials_non_expired")
    private Boolean credentialsNotExpired;

    @Column(name = "account_non_locked")
    private Boolean accountNotLocked;

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

package com.geekbrains.knigopoisk.entities;

import com.geekbrains.knigopoisk.validation.BirthYearValidation;
import com.geekbrains.knigopoisk.validation.EmailValidation;
import com.geekbrains.knigopoisk.validation.FieldMatchValidation;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@FieldMatchValidation(first = "password", second = "matchingPassword", message = "Пароль должен совпадать")
public class SystemUser {
    @NotNull(message = "требуется")
    @Size(min = 3, message = "не менее 3 символов")
//    @Pattern(regexp = "^[a-zA-Z0-9]{5}", message = "only 5 letters/digits")
    private String userName;

    @NotNull(message = "требуется")
    @Size(min = 1, message = "требуется")
    private String password;

    @NotNull(message = "требуется")
    @Size(min = 1, message = "требуется")
    private String matchingPassword;

    @NotNull(message = "требуется")
    @Size(min = 3, message = "требуется")
    private String firstName;

    @NotNull(message = "требуется")
    @Size(min = 3, message = "требуется")
    private String lastName;

    @EmailValidation
    @NotNull(message = "требуется")
    @Size(min = 6, message = "требуется")
    private String email;

    @BirthYearValidation
    @NotNull(message = "требуется")
    private Integer birthYear;
}

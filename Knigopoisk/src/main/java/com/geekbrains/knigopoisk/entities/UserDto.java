package com.geekbrains.knigopoisk.entities;

import com.geekbrains.knigopoisk.validation.BirthDateValidation;
import com.geekbrains.knigopoisk.validation.EmailValidation;
import com.geekbrains.knigopoisk.validation.FieldMatchValidation;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@FieldMatchValidation(first = "password", second = "matchingPassword", message = "пароль должен совпадать")
public class UserDto {
    @NotNull(message = "требуется")
    @Size(min = 3, message = "не менее 3 символов")
    private String userName;

    @NotNull(message = "требуется")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",
            message = "не менее 8 символов, включая заглавные и прописные буквы на латинице, цифры и спецсимволы")
    private String password;

    @NotNull(message = "требуется")
    private String matchingPassword;

    @NotNull(message = "требуется")
    @Size(min = 3, message = "требуется")
    private String firstName;

    @NotNull(message = "требуется")
    @Size(min = 3, message = "требуется")
    private String lastName;

    @EmailValidation
    @NotNull(message = "требуется")
    private String email;

    @BirthDateValidation
    @NotNull(message = "требуется")
    private String birthDate;
}

package com.geekbrains.knigopoisk.dto;

import com.geekbrains.knigopoisk.validation.FieldMatchValidation;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@FieldMatchValidation(first = "password", second = "matchingPassword", message = "пароль должен совпадать")
public class UserPasswordDto {

    @NotNull(message = "требуется")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",
            message = "не менее 8 символов, включая заглавные и прописные буквы на латинице, цифры и спецсимволы")
    private String password;

    @NotNull(message = "требуется")
    private String matchingPassword;
}

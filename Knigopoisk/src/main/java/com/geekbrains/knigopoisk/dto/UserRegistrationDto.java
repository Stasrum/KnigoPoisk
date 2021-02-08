package com.geekbrains.knigopoisk.dto;

import com.geekbrains.knigopoisk.validation.BirthDayValidation;
import com.geekbrains.knigopoisk.validation.EmailValidation;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserRegistrationDto extends UserPasswordDto {
    @NotNull(message = "требуется")
    @Size(min = 3, message = "не менее 3 символов")
    private String userName;

    @NotNull(message = "требуется")
    @Size(min = 3, message = "требуется")
    private String firstName;

    @NotNull(message = "требуется")
    @Size(min = 3, message = "требуется")
    private String lastName;

    @EmailValidation
    @NotNull(message = "требуется")
    private String email;

    @BirthDayValidation
    @NotNull(message = "требуется")
    private String birthDay;
}

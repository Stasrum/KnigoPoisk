package com.geekbrains.knigopoisk.dto;

import com.geekbrains.knigopoisk.entities.User;
import com.geekbrains.knigopoisk.validation.BirthDayValidation;
import com.geekbrains.knigopoisk.validation.EmailValidation;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class UserDetailsDto {
    @NotNull(message = "требуется")
    private Long id;

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

    public static User fromDto(UserDetailsDto userDetailsDto) {
        User user = new User();
        user.setId(userDetailsDto.getId());
        user.setUsername(userDetailsDto.getUserName());
        user.setFirstName(userDetailsDto.getFirstName());
        user.setLastName(userDetailsDto.getLastName());
        user.setEmail(userDetailsDto.getEmail());
        user.setBirthDay(LocalDate.parse(userDetailsDto.getBirthDay()));
        return user;
    }
}

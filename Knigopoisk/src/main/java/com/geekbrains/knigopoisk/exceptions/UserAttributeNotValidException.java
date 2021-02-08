package com.geekbrains.knigopoisk.exceptions;

import lombok.Data;
import org.springframework.validation.ObjectError;

import java.util.List;

@Data
public class UserAttributeNotValidException extends RuntimeException {
    List<ObjectError> errors;

    public UserAttributeNotValidException(String message, List<ObjectError> errors) {
        super(message);
        this.errors = errors;
    }
}

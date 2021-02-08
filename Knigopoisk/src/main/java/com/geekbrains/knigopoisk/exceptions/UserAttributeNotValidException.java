package com.geekbrains.knigopoisk.exceptions;

import lombok.Data;
import org.springframework.validation.BindingResult;

@Data
public class UserAttributeNotValidException extends RuntimeException {
    private BindingResult bindingResult;

    public UserAttributeNotValidException(String message, BindingResult bindingResult) {
        super(message);
        this.bindingResult = bindingResult;
    }
}

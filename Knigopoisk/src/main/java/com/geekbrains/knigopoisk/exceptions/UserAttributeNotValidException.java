package com.geekbrains.knigopoisk.exceptions;

import org.springframework.validation.BindingResult;

public class UserAttributeNotValidException extends AttributeNotValidException {
    public UserAttributeNotValidException(String message, BindingResult bindingResult) {
        super(message, bindingResult);
    }
}

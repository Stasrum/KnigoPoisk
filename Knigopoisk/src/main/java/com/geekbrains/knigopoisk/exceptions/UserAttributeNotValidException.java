package com.geekbrains.knigopoisk.exceptions;

import com.geekbrains.knigopoisk.exceptions.types.AttributeNotValidException;
import org.springframework.validation.BindingResult;

public class UserAttributeNotValidException extends AttributeNotValidException {
    public UserAttributeNotValidException(String message, BindingResult bindingResult) {
        super(message, bindingResult);
    }
}

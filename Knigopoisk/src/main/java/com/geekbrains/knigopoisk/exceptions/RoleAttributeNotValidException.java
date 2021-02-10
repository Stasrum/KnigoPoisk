package com.geekbrains.knigopoisk.exceptions;

import com.geekbrains.knigopoisk.exceptions.types.AttributeNotValidException;
import org.springframework.validation.BindingResult;

public class RoleAttributeNotValidException extends AttributeNotValidException {
    public RoleAttributeNotValidException(String message, BindingResult bindingResult) {
        super(message, bindingResult);
    }
}

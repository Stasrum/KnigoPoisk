package com.geekbrains.knigopoisk.exceptions;

import org.springframework.validation.BindingResult;

public class RoleAttributeNotValidException extends AttributeNotValidException {
    public RoleAttributeNotValidException(String message, BindingResult bindingResult) {
        super(message, bindingResult);
    }
}

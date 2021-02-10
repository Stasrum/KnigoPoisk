package com.geekbrains.knigopoisk.exceptions;

import lombok.Data;
import org.springframework.validation.BindingResult;

@Data
public class RoleAttributeNotValidException extends RuntimeException {
    private BindingResult bindingResult;

    public RoleAttributeNotValidException(String message, BindingResult bindingResult) {
        super(message);
        this.bindingResult = bindingResult;
    }
}

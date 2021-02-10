package com.geekbrains.knigopoisk.exceptions.types;

import lombok.*;
import org.springframework.validation.BindingResult;

@Getter
public class AttributeNotValidException extends RuntimeException {
    private final BindingResult bindingResult;

    public AttributeNotValidException(String message, BindingResult bindingResult) {
        super(message);
        this.bindingResult = bindingResult;
    }
}

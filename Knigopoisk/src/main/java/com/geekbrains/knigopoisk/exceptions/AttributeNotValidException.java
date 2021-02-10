package com.geekbrains.knigopoisk.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class AttributeNotValidException extends RuntimeException {
    private BindingResult bindingResult;

    public AttributeNotValidException(String message, BindingResult bindingResult) {
        super(message);
        this.bindingResult = bindingResult;
    }
}

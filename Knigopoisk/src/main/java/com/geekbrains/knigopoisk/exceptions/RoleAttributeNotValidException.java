package com.geekbrains.knigopoisk.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.validation.BindingResult;

@EqualsAndHashCode(callSuper = true)
@Data
public class RoleAttributeNotValidException extends AttributeNotValidException {
    public RoleAttributeNotValidException(String message, BindingResult bindingResult) {
        super(message, bindingResult);
    }
}

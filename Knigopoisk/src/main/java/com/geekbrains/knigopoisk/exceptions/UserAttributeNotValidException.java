package com.geekbrains.knigopoisk.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserAttributeNotValidException extends AttributeNotValidException {
    public UserAttributeNotValidException(String message, BindingResult bindingResult) {
        super(message, bindingResult);
    }
}

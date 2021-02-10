package com.geekbrains.knigopoisk.exceptions;

import com.geekbrains.knigopoisk.exceptions.types.ElementAlreadyExistsException;

public class UserAlreadyExistsException extends ElementAlreadyExistsException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}

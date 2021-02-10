package com.geekbrains.knigopoisk.exceptions;

public class UserAlreadyExistsException extends ElementAlreadyExistsException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}

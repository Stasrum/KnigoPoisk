package com.geekbrains.knigopoisk.exceptions;

public class UserNotAuthorizedException extends RuntimeException {
    public UserNotAuthorizedException(String message) {
        super(message);
    }
}

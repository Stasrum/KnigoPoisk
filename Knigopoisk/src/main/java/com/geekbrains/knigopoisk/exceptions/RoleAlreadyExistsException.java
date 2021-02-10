package com.geekbrains.knigopoisk.exceptions;

import com.geekbrains.knigopoisk.exceptions.types.ElementAlreadyExistsException;

public class RoleAlreadyExistsException extends ElementAlreadyExistsException {
    public RoleAlreadyExistsException(String message) {
        super(message);
    }
}

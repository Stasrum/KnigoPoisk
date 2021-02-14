package com.geekbrains.knigopoisk.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ApiError extends ApiMessage {

    private HttpStatus status;
    private List<String> errors;

    public ApiError(HttpStatus status, String message, List<String> errors) {
        super(message);
        this.status = status;
        this.errors = errors;
    }

    public ApiError(HttpStatus status, String message, String error) {
        super(message);
        this.status = status;
        errors = Arrays.asList(error);
    }
}

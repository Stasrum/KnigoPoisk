package com.geekbrains.knigopoisk.util;

import com.geekbrains.knigopoisk.responsies.ReqErrorResponse;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExcepHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PSQLException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<?> handle500() {
        return new ResponseEntity<>(new ReqErrorResponse(HttpStatus.BAD_REQUEST.value(), "Ошибка записи в базу данных"), HttpStatus.BAD_REQUEST);
    }
}

package com.geekbrains.knigopoisk.exceptions;

public class AuthorNotFoundException extends RuntimeException{
        public AuthorNotFoundException(String message) {
            super(message);
        }
}

package com.geekbrains.knigopoisk.validation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.OffsetDateTime;
import java.util.Calendar;

public class BirthYearValidator implements ConstraintValidator<BirthYearValidation, String> {

    @Override
    public boolean isValid(String birthYear, ConstraintValidatorContext context) {
        try {
            int year = Integer.parseInt(birthYear);
            return year > 1900 && year <= OffsetDateTime.now().getYear();
        } catch (Exception e) {
            return false;
        }
    }
}

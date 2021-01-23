package com.geekbrains.knigopoisk.validation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Calendar;

public class BirthYearValidator implements ConstraintValidator<EmailValidation, Integer> {

    @Override
    public boolean isValid(final Integer birthYear, final ConstraintValidatorContext context) {
        return birthYear > 1900 && birthYear <= Calendar.getInstance().get(Calendar.YEAR);
    }
}
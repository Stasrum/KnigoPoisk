package com.geekbrains.knigopoisk.validation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Calendar;

public class BirthYearValidator implements ConstraintValidator<BirthYearValidation, String> {

    @Override
    public boolean isValid(final String birthYear, final ConstraintValidatorContext context) {
        try {
            Integer year = Integer.parseInt(birthYear);
            return year != null && year > 1900 && year <= Calendar.getInstance().get(Calendar.YEAR);
        } catch (Exception e) {
            return false;
        }
    }
}
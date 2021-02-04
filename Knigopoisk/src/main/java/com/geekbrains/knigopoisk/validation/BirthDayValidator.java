package com.geekbrains.knigopoisk.validation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BirthDayValidator implements ConstraintValidator<BirthDayValidation, String> {
    public static final String DATE_PATTERN = "^((19|20)\\d{2})\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$";

    @Override
    public boolean isValid(final String birthDate, final ConstraintValidatorContext context) {
        if (birthDate == null) {
            return false;
        }

        Matcher matcher = Pattern.compile(DATE_PATTERN).matcher(birthDate);

        if (matcher.find()) {
            int year = Integer.parseInt(matcher.group(1));
            int month = Integer.parseInt(matcher.group(3));
            int day = Integer.parseInt(matcher.group(4));

            if ((month == 4 || month == 6 || month == 9 || month == 11) && day == 31) {
                return false;
            } else if (month == 2) {
                if (day == 30 || day == 31 || (year % 4 != 0 && day == 29)) {
                    return false;
                }
            }

            return LocalDate.now().isAfter(LocalDate.of(year, month, day));
        }

        return false;
    }
}
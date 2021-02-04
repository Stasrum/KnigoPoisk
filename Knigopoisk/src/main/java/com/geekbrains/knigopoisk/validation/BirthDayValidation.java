package com.geekbrains.knigopoisk.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = BirthDayValidator.class)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface BirthDayValidation {
	String message() default "требуется ненаступившая дата в формате YYYY-MM-DD";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}

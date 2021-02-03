package com.geekbrains.knigopoisk.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = BirthDateValidator.class)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface BirthDateValidation {
	String message() default "требуется корректная дата в формате YYYY.MM.DD";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}

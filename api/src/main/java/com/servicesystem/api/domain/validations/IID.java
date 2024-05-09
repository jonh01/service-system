package com.servicesystem.api.domain.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = IDValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface IID {

    String message() default "ID com formato incorreto!";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
    
}

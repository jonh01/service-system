package com.servicesystem.api.domain.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = PhoneValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Phone {

    String message() default "Formato de telefone incorreto! Padrão: (xx)9xxxx-xxxx";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
    
}

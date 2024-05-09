package com.servicesystem.api.domain.validations;

import java.util.UUID;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IDValidator implements ConstraintValidator<IID, String>{
    
    @Override
    public void initialize(IID constraintAnnotation) {
      // TODO document why this method is empty
    }
    
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null)
            return false;

        try {
            UUID uuid = UUID.fromString(value);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
}

package com.servicesystem.api.domain.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone, String>{
    
    private String standard = "^\\([1-9]{2}\\)(9)[0-9]{4}\\-[0-9]{4}$";

    @Override
    public void initialize(Phone constraintAnnotation) {
      // TODO document why this method is empty
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null)
            return false;
        
        return value.matches(standard);
    }
}

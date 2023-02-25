package com.jorgetrujillo.demo.common;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class IsInValidator implements ConstraintValidator<IsIn, String> {

    private String[] allowedValues;

    @Override
    public void initialize(IsIn constraintAnnotation) {
        this.allowedValues = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return Arrays.asList(allowedValues).contains(value);
    }
}

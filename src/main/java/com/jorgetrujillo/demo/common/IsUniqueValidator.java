package com.jorgetrujillo.demo.common;

import com.jorgetrujillo.demo.repositories.ClientRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

public class IsUniqueValidator implements ConstraintValidator<IsUnique, String> {

    @Autowired
    ClientRepository clientRepository;
    @Override
    public void initialize(IsUnique constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return clientRepository.countIfExists(value) <= 0;
    }
}

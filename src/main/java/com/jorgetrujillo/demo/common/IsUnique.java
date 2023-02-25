package com.jorgetrujillo.demo.common;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsUniqueValidator.class)
public @interface IsUnique {
    String message() default "El numeroId ya está en uso";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

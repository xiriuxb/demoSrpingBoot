package com.jorgetrujillo.demo.common;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsInValidator.class)
public @interface IsIn {

    String[] value();
    String message() default "Value must be one of {value}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

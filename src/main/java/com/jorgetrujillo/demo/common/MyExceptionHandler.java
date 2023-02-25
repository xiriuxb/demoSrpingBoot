package com.jorgetrujillo.demo.common;

import com.jorgetrujillo.demo.dtos.ExceptionDto;
import com.jorgetrujillo.demo.models.MyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleExceptions( MyException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionDto("sda", 400, e.getMessage(), e.getErrors(),"sd"));

    }

}

package com.jorgetrujillo.demo.models;

import java.util.List;

public class MyException extends Exception{
    private List<String> errors;

    public MyException(String message, List<String> errors) {
        super(message);
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}

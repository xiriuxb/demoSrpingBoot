package com.jorgetrujillo.demo.dtos;

import java.lang.reflect.Array;
import java.util.List;

public record ExceptionDto(
        String timestamp,
        int status,
        String message,
        List<String> errors,
        String path
) {
}

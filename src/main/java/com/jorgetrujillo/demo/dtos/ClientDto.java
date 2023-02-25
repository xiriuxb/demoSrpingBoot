package com.jorgetrujillo.demo.dtos;

import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

public record ClientDto(
        Long id,
        String names,
        String idType,
        String idNumber,
        String phoneNumber,
        String email,
        String mainProvince,
        String mainCity,
        String mainAddress
) {}


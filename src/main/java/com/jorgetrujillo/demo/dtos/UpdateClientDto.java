package com.jorgetrujillo.demo.dtos;

import com.jorgetrujillo.demo.common.IsIn;
import com.jorgetrujillo.demo.common.IsUnique;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UpdateClientDto(
        String names,
        @IsIn(value = {"Cedula", "RUC"})
        String idType,
        @IsUnique
        String idNumber,
        String phoneNumber,
        @Email
        String email
) {
}

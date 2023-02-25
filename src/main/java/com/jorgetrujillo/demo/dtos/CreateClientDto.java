package com.jorgetrujillo.demo.dtos;

import com.jorgetrujillo.demo.common.IsIn;
import com.jorgetrujillo.demo.common.IsUnique;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateClientDto(
        @NotBlank
        String names,

        @NotBlank
        @IsIn(value = {"Cedula", "RUC"})
        String idType,
        @IsUnique
        @NotBlank
        String idNumber,
        @NotBlank
        String phoneNumber,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String mainProvince,
        @NotBlank
        String mainCity,
        @NotBlank
        String mainAddress
) {
}

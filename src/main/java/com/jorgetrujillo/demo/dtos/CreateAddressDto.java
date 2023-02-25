package com.jorgetrujillo.demo.dtos;

import jakarta.validation.constraints.NotBlank;

public record CreateAddressDto(
        @NotBlank
        String province,
        @NotBlank
        String city,
        @NotBlank
        String address
) {
}

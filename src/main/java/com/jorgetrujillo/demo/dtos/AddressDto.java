package com.jorgetrujillo.demo.dtos;


public record AddressDto (
    long id,
    String province,
    String city,
    String address,
    boolean isMain
){}

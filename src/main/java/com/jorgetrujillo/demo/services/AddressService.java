package com.jorgetrujillo.demo.services;

import com.jorgetrujillo.demo.dtos.CreateAddressDto;
import com.jorgetrujillo.demo.models.Address;
import com.jorgetrujillo.demo.models.MyException;
import com.jorgetrujillo.demo.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;

    public Address createAddress(CreateAddressDto dto, boolean isMain) throws MyException {
        try{
            Address savedAddress = addressRepository.save(
                    new Address( dto.province(), dto.city(), dto.address(), isMain)
            );
            return savedAddress;
        } catch (Exception e){
            throw new MyException("Error al insertar en la base de datos la dirección", List.of());
        }
    }

}

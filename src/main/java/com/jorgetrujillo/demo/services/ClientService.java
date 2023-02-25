package com.jorgetrujillo.demo.services;

import com.jorgetrujillo.demo.common.MyExceptionHandler;
import com.jorgetrujillo.demo.dtos.*;
import com.jorgetrujillo.demo.models.Address;
import com.jorgetrujillo.demo.models.Client;
import com.jorgetrujillo.demo.models.MyException;
import com.jorgetrujillo.demo.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AddressService addressService;

    public ResponseEntity<?> getAll(){
        try {
            List<ClientDto> clients = clientRepository.findAll().stream().map(
                    Client::toClientDto
            ).toList();

            return  ResponseEntity.ok(clients);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    public ResponseEntity<?> createNewClient(CreateClientDto createClientDto){
        try {
            Client savedClient = this.storeClient(createClientDto);
            Address newAddress = addressService.createAddress(
                    new CreateAddressDto(createClientDto.mainProvince(), createClientDto.mainCity(), createClientDto.mainAddress()),
                    true
            );

            savedClient.addAddress(newAddress);
            clientRepository.save(savedClient);

             return ResponseEntity.status(HttpStatus.CREATED).body(savedClient.toClientDto());
        } catch(MyException e) {
            return new MyExceptionHandler().handleExceptions(e);
        }
    }

    public ResponseEntity<?> update(Long clientId, UpdateClientDto dto) {
        try{
            Client existingClient = clientRepository.findById(clientId).orElseThrow(
                    () -> new Exception("Cliente no encontrado")
            );
            if(dto.phoneNumber() !=null) existingClient.setMobileNumber(dto.phoneNumber());
            if(dto.email() != null) existingClient.setEmail(dto.email());
            if(dto.names() != null) existingClient.setNames(dto.names());
            if(dto.idType() != null) existingClient.setIdType(dto.idType());
            if(dto.idNumber() !=null) existingClient.setIdNumber(dto.idNumber());
            ClientDto updatedClient = clientRepository.save(existingClient).toClientDto();
            return  ResponseEntity.status(HttpStatus.OK).body(updatedClient);
        } catch (Exception e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }

    }

    public ResponseEntity<?> getFiltered(String searchTerm){
        try{
            List<ClientDto> clients =
                    clientRepository.findByNamesOrIdNumber(searchTerm)
                            .stream()
                            .filter(client -> !client.isDeleted())
                            .map(Client::toClientDto).toList();
            return ResponseEntity.status(HttpStatus.OK).body(clients);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    private Client storeClient(CreateClientDto dto) throws MyException {
        try{
            return clientRepository.save(
                    new Client(
                            dto.names(),
                            dto.idType(),
                            dto.idNumber(),
                            dto.email(),
                            dto.phoneNumber()
                    )
            );
        } catch(Exception e) {
            throw new MyException("Error al crear usuario en la base de datos",List.of());
        }
    }


    public ResponseEntity<?> addAddress(Long clientId, CreateAddressDto dto){
        try{
            Client existingClient = clientRepository.findById(clientId).orElseThrow(
                    () -> new Exception("Cliente no encontrado")
            );
            Address newAddress = addressService.createAddress(dto,false);
            existingClient.addAddress(newAddress);
            clientRepository.save(existingClient);

            return ResponseEntity.ok("Updated");
        } catch (Exception e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    public ResponseEntity<?> getClientAddresses(Long clientId){
        try{
            Client client = clientRepository.findById(clientId).orElseThrow();
            List<AddressDto> clientAddresses= client.getAddresses().stream().map(Address::toAddressDto).toList();
            return ResponseEntity.status(HttpStatus.OK).body(clientAddresses);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    public ResponseEntity<?> deleteClient(Long clientId){
        try{
            Client client = clientRepository.findById(clientId).orElseThrow();
            client.setDeleted(true);
            client.setIdNumber(client.getIdNumber().concat("_deleted"));
            clientRepository.save(client);
            return ResponseEntity.status(HttpStatus.OK).body("Eliminado");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

}

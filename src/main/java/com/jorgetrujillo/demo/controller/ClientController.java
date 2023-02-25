package com.jorgetrujillo.demo.controller;


import com.jorgetrujillo.demo.dtos.ClientDto;
import com.jorgetrujillo.demo.dtos.CreateAddressDto;
import com.jorgetrujillo.demo.dtos.CreateClientDto;
import com.jorgetrujillo.demo.dtos.UpdateClientDto;
import com.jorgetrujillo.demo.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/clients")
    public ResponseEntity<?> listAll() {
        return clientService.getAll();
    }

    @PostMapping("/clients")
    public ResponseEntity<?> createClient(@Valid @RequestBody CreateClientDto client) {

            return clientService.createNewClient(client);
    }
    @PatchMapping("/clients/{id}")
    public ResponseEntity<?> updateClient(@PathVariable Long id, @Valid @RequestBody UpdateClientDto dto){
        return clientService.update(id, dto);
    }
    @GetMapping("/clients-search")
    public  ResponseEntity<?> clientsSearch(@RequestParam("search") String search){
        return clientService.getFiltered(search);
    }

    @GetMapping("/clients/addresses/{id}")
    public ResponseEntity<?> getClientAddresses(@PathVariable Long id){
        return clientService.getClientAddresses(id);
    }

    @PatchMapping("/clients/addresses/{id}")
    public ResponseEntity<?> addClientAddresses(@PathVariable Long id, @RequestBody @Valid CreateAddressDto dto){
        return  clientService.addAddress(id,dto);
    }

    @PatchMapping("/clients/delete/{id}")
    public  ResponseEntity<?> deleteClient(@PathVariable Long id){
        return clientService.deleteClient(id);
    }

}

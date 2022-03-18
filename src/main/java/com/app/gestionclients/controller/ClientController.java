package com.app.gestionclients.controller;

import com.app.gestionclients.dto.ClientDto;
import com.app.gestionclients.entity.Client;
import com.app.gestionclients.service.ClientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/clients"})
public class ClientController {
    @Autowired
    ClientService clientService;
    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Client> getClient(@PathVariable long id) {
        ClientDto clientDto = this.clientService.getClientById(id);
        ModelMapper modelMapper = new ModelMapper();
        System.out.println(clientDto.toString());
        return new ResponseEntity(clientDto, HttpStatus.OK);
    }

    @GetMapping({"/all"})
    public ResponseEntity<List<ClientDto>> getAllUsers() {
        List<ClientDto> clientDtos = clientService.getAllClients();
        return ResponseEntity.ok(clientDtos);
    }

    @PatchMapping(
            path = {"/{id}"}
    )
    public ResponseEntity<ClientDto> updateUser(@PathVariable Long id, @RequestBody ClientDto clientDto) {

        ClientDto updateClient = this.clientService.updateClient(id, clientDto);
        return new ResponseEntity(updateClient, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(
            path = {"/{id}"}
    )

    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        this.clientService.deleteClient(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}

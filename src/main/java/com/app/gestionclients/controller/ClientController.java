package com.app.gestionclients.controller;

import com.app.gestionclients.dto.ClientDto;
import com.app.gestionclients.entity.Client;
import com.app.gestionclients.enums.sexe;
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

    @PostMapping(path = "/save")
    public ResponseEntity<List<ClientDto>> addNewClient(@RequestBody List<Client> client){
        try {
            if (!client.isEmpty()) {

                List<ClientDto> newClients = clientService.saveClients(client);

                if(newClients == null){
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                }
                return ResponseEntity.ok().body(newClients);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("Test","Test").build();
        }catch (IllegalStateException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("Exception",e.getMessage()).build();
        }

    }

    @GetMapping({"/"})
    public ResponseEntity<List<ClientDto>> getAllClients(@RequestParam(value = "page") int page,@RequestParam(value = "limit") int limit) {
        List<ClientDto> profiles = clientService.getAllClients(page,limit);
        return ResponseEntity.ok(profiles);
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<ClientDto> getClientById(@PathVariable Long id) {
        ClientDto clientDto = this.clientService.getClientById(id);
        return new ResponseEntity(clientDto, HttpStatus.OK);
    }

    @GetMapping(path = {"/email/{email}"})
    public ResponseEntity<Client> getClientByEmail(@PathVariable String email) {
        ClientDto clientDto = this.clientService.getClientByEmail(email);
        ModelMapper modelMapper = new ModelMapper();
        System.out.println(clientDto.toString());
        return new ResponseEntity(clientDto, HttpStatus.OK);
    }

    @GetMapping({"/all/{sexe}"})
    public ResponseEntity<List<ClientDto>> getAllUsersBySexe(@PathVariable sexe sexe) {
        List<ClientDto> clientDtos = clientService.getAllClientsBySexe(sexe);
        return ResponseEntity.ok(clientDtos);
    }

    @DeleteMapping(
            path = {"/{id}"}
    )
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        this.clientService.deleteClient(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(
            path = {"/{id}"}
    )
    public ResponseEntity<ClientDto> updateUser(@PathVariable Long id, @RequestBody ClientDto clientDto) {
        ClientDto updateClient = this.clientService.updateClient(id, clientDto);
        return new ResponseEntity(updateClient, HttpStatus.ACCEPTED);
    }

}

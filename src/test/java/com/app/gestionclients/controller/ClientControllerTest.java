package com.app.gestionclients.controller;
import com.app.gestionclients.dto.ClientDto;
import com.app.gestionclients.entity.Client;
import com.app.gestionclients.enums.sexe;
import com.app.gestionclients.service.ClientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.junit.MockitoJUnitRunner.*;

@RunWith(Silent.class)
@WebMvcTest(ClientController.class)
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    @Test
    void addNewClient() throws Exception{
        ClientDto clientDto1 = new ClientDto( 1L,"Medsougtani@gmail.com","+212639003782","Med idbihi",12, sexe.Homme,true);
        when(clientService.getClientById(clientDto1.getId())).thenReturn(clientDto1);
        mockMvc.perform(get("/clients/" + clientDto1.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getAllClients() throws Exception {

        ClientDto clientDto1 = new ClientDto( 1L,"Medsougtani@gmail.com","+212639003782","Med idbihi",12, sexe.Homme,true);
        ClientDto clientDto2 = new ClientDto( 2L,"Idbihi@gmail.com","+212671310631","Haj matich",12,sexe.Homme,true);
        List<ClientDto> clientDtos = new ArrayList<>();
        clientDtos.add(clientDto1);
        clientDtos.add(clientDto2);
        when(clientService.getAllClients(1,5)).thenReturn(clientDtos);
        mockMvc.perform(get("/clients/"))
                .andExpect(status().isOk());

    }

    @Test
    void getClientById() throws Exception {
        ClientDto clientDto1 = new ClientDto( 1L,"Medsougtani@gmail.com","+212639003782","Med idbihi",12, sexe.Homme,true);
        when(clientService.getClientById(clientDto1.getId())).thenReturn(clientDto1);
        mockMvc.perform(get("/clients/" + clientDto1.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getClientByEmail() throws Exception{
        ClientDto clientDto1 = new ClientDto( 1L,"Medsougtani@gmail.com","+212639003782","Med idbihi",12, sexe.Homme,true);
        when(clientService.getClientByEmail(clientDto1.getEmail())).thenReturn(clientDto1);
        mockMvc.perform(get("/clients/email/" + clientDto1.getEmail())
                        .contentType(MediaType.APPLICATION_JSON))
                          .andExpect(status().isOk());
    }

    @Test
    void getAllUsersBySexe() throws Exception {
        ClientDto clientDto1 = new ClientDto( 1L,"Medsougtani@gmail.com","+212639003782","Med idbihi",12, sexe.Homme,true);
        ClientDto clientDto2 = new ClientDto( 2L,"Idbihi@gmail.com","+212671310631","Haj matich",12,sexe.Homme,true);
        List<ClientDto> clientDtos = new ArrayList<>();
        clientDtos.add(clientDto1);
        clientDtos.add(clientDto2);
        when(clientService.getAllClientsBySexe(sexe.Homme)).thenReturn(clientDtos);
        mockMvc.perform(get("/clients/all/"+clientDto1.getSexe()))
                .andExpect(status().isOk());
    }

    @Test
    void deleteUser() {
    }

    @Test
    void updateUser() throws Exception {
        Client client = new Client( 1L,"Medsougtani@gmail.com","+212639003782","Med idbihi",12, sexe.Homme,true);
        List<Client> clients = new ArrayList<>();
        clients.add(client);
        mockMvc.perform(patch("/clients/"+ client.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(client)))
                        .andExpect(status().isAccepted());
    }

}
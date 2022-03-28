package com.app.gestionclients.service;

import com.app.gestionclients.dto.ClientDto;
import com.app.gestionclients.entity.Client;
import com.app.gestionclients.enums.sexe;
import com.app.gestionclients.repository.ClientRepository;
import com.app.gestionclients.service.mapper.IMapClassWithDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.InstanceOfAssertFactories.OPTIONAL;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.Silent.class)
class ClientServiceTest {

    @InjectMocks
    private ClientServiceImpl clientService;

    @Mock
    private ClientRepository clientRepository;


    @BeforeEach
    public void setup() {
        clientService = new ClientServiceImpl(clientRepository);
    }

    @Test
    void getClientById() {
        
        ClientDto clientDto1 = new ClientDto(1L, "Medsougtani@gmail.com","+212659697087","Med idbihi",62,sexe.Homme,true);
        Client client = new Client(1L, "Medsougtani@gmail.com","+212659697087","Med idbihi",62,sexe.Homme,true);
        Mockito.when(clientRepository.findById(1L)).thenReturn(java.util.Optional.of(client));
        assertThat(clientService.getClientById(clientDto1.getId())).isNotNull();
        assertNotNull(clientService.getClientById(1L));
    }

    @Test
    void getClientByEmail() {

        ClientDto clientDto1 = new ClientDto( 1L,"Medsougtani@gmail.com","+212639003782","Med idbihi",28, sexe.Homme,true);
        Client client = new Client(1L, "Medsougtani@gmail.com","+212659697087","Med idbihi",62,sexe.Homme,true);
        Mockito.when(clientRepository.findClientByEmailAndIsActiveIsTrue(clientDto1.getEmail())).thenReturn(client);
        assertThat(clientService.getClientByEmail("Medsougtani@gmail.com").getEmail()).isEqualTo("Medsougtani@gmail.com");

    }

    @Test
    void deleteClient() {
        ClientDto clientDto1 = new ClientDto(1L, "Medsougtani@gmail.com","+212659697087","Med idbihi",62,sexe.Homme,true);
        Client client = new Client(1L, "Medsougtani@gmail.com","+212659697087","Med idbihi",62,sexe.Homme,true);
        Mockito.when(clientRepository.findClientById(1L)).thenReturn(client);
        assertThat(clientService.deleteClient(1L)).isEqualTo(true);
    }

    @Test
    void getAllClients() {
        ClientDto clientDto1 = new ClientDto(1L, "Medsougtani@gmail.com","+212659697087","Med idbihi",62,sexe.Homme,true);
        ClientDto clientDto2 = new ClientDto(1L, "Medsougtani@gmail.com","+212659697087","Med idbihi",62,sexe.Homme,true);
        List<ClientDto> clientDtos = new ArrayList<>();
        clientDtos.add(clientDto1);
        clientDtos.add(clientDto2);
        Pageable pageable = PageRequest.of(1,10);
        //Mockito.when(clientRepository.findAll(pageable)).thenReturn();
        assertThat(clientService.getAllClients(1,6)).isNotNull();
    }

    @Test
    void getAllClientsBySexe() {

        ClientDto clientDto1 = new ClientDto( 1L,"Medsougtani@gmail.com","+212639003782","Med idbihi",28, sexe.Homme,true);
        ClientDto clientDto2 = new ClientDto( 2L,"MedsoDDugtani@gmail.com","+212129003782","Med idbDDihi",28, sexe.Homme,true);
        List<ClientDto> clients = new ArrayList<>();
        clients.add(clientDto1);
        clients.add(clientDto2);
        Mockito.when(clientService.getAllClientsBySexe( sexe.Homme)).thenReturn(clients);
        assertThat(clientService.getAllClientsBySexe( sexe.Homme)).isNotNull();

    }
}
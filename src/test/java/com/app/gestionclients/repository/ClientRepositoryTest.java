package com.app.gestionclients.repository;

import com.app.gestionclients.entity.Client;
import com.app.gestionclients.enums.sexe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.Silent.class)
@DataJpaTest
class ClientRepositoryTest {

    @Mock
    private ClientRepository clientRepository;

    @Test
    void findClientById() {

        Client client1 = new Client( 1L,"Medsougtani@gmail.com","+212639003782","Med idbihi",24, sexe.Homme,true);
        Mockito.when(clientRepository.findById(client1.getId())).thenReturn(Optional.of(client1));
        assertThat(clientRepository.findById(client1.getId())).isNotNull();

    }

    @Test
    void findClientByEmailAndIsActiveIsTrue() {
        Client client1 = new Client( 1L,"Medsougtani@gmail.com","+212639003782","Med idbihi",29, sexe.Homme,true);
        Mockito.when(clientRepository.findClientByEmailAndIsActiveIsTrue("Medsougtani@gmail.com")).thenReturn(client1);
        assertThat(clientRepository.findClientByEmailAndIsActiveIsTrue("Medsougtani@gmail.com").getEmail()).isEqualTo("Medsougtani@gmail.com");
    }

    @Test
    void findAllBySexe() {

        Client client1 = new Client( 1L,"Medsougtani@gmail.com","+212639003782","Med idbihi",26, sexe.Homme,true);
        Client client2 = new Client( 1L,"Medsougddtani@gmail.com","+212639003782","Med idbihi",28, sexe.Homme,true);
        List<Client> clients = new ArrayList<>();
        clients.add(client1);
        clients.add(client2);
        Mockito.when(clientRepository.findAllBySexe(sexe.femme)).thenReturn(clients);
        assertThat(clientRepository.findAllBySexe(sexe.femme)).isNotNull();

    }
}
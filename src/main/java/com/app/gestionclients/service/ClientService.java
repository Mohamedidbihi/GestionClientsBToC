package com.app.gestionclients.service;

import com.app.gestionclients.dto.ClientDto;
import com.app.gestionclients.entity.Client;
import com.app.gestionclients.enums.sexe;

import java.util.List;

public interface ClientService {

    ClientDto getClientById(Long id);

    ClientDto getClientByEmail(String email);

    ClientDto updateClient(Long id,ClientDto clientDto);

    boolean deleteClient(Long id);

    List<ClientDto> getAllClients(int page,int limit);

    List<ClientDto> getAllClientsBySexe(sexe sexe);

    List<ClientDto> saveClients(List<Client> clients);

}

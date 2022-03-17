package com.app.gestionclients.service;

import com.app.gestionclients.dto.ClientDto;

import java.util.List;

public interface ClientService {

    ClientDto getClientById(long id);

    ClientDto createClient(ClientDto clientDto);

    ClientDto updateClient(long id,ClientDto clientDto);

    void deleteClient(long id);

    List<ClientDto> getAllClients();

}

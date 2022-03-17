package com.app.gestionclients.service;

import com.app.gestionclients.dto.ClientDto;
import com.app.gestionclients.entity.Client;
import com.app.gestionclients.repository.ClientRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service("ClientService")
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientRepository clientRepository;



    @Override
    public ClientDto getClientById(long id) {
        Client client = this.clientRepository.findById(id);
        if (client == null) {
            throw new RuntimeException("error");
        } else {
            ClientDto clientDto = new ClientDto();
            BeanUtils.copyProperties(client, clientDto);
            return clientDto;
        }
    }

    @Override
    public ClientDto createClient(ClientDto clientDto) {
        return null;
    }

    @Override
    public ClientDto updateClient(long id, ClientDto clientDto) {
        Client client = this.clientRepository.findById(id);
        if (client == null) {
            throw new RuntimeException("No client was found!");
        } else {
            client.setActive(clientDto.getActive());
            client.setAge(clientDto.getAge());
            client.setFullName(clientDto.getFullName());
            client.setPhone(clientDto.getPhone());
            client.setSexe(clientDto.getSexe());
            client.setEmail(clientDto.getEmail());
            Client client1 = (Client) this.clientRepository.save(client);
            ClientDto clientDt = new ClientDto();
            BeanUtils.copyProperties(client1, clientDt);
            return clientDt;
        }
    }

    @Override
    public void deleteClient(long id) {
        Client client = this.clientRepository.findById(id);
        if (client == null) {
            throw new RuntimeException("null");
        } else {
            this.clientRepository.delete(client);
        }
    }

    @Override
    public List<ClientDto> getAllClients() {
        List<Client> clients = this.clientRepository.findAll();
        Type listType = (new TypeToken<List<ClientDto>>() {
        }).getType();
        List<ClientDto> clientDtos = (List)(new ModelMapper()).map(clients, listType);
        return clientDtos;
    }
}

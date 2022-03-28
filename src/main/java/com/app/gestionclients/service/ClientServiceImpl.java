package com.app.gestionclients.service;

import com.app.gestionclients.dto.ClientDto;
import com.app.gestionclients.entity.Client;
import com.app.gestionclients.enums.sexe;
import com.app.gestionclients.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("ClientService") @AllArgsConstructor
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientRepository clientRepository;


    @Override
    public ClientDto getClientById(Long id) {
        Client client = this.clientRepository.findById(id).get();
        if (client == null) {
            throw new RuntimeException("error");
        } else {
            ClientDto clientDto = new ClientDto();
            BeanUtils.copyProperties(client, clientDto);
            return clientDto;
        }
    }

    @Override
    public ClientDto getClientByEmail(String email) {

        Client client = this.clientRepository.findClientByEmailAndIsActiveIsTrue(email);
        ClientDto clientDto = new ClientDto();
        BeanUtils.copyProperties(client, clientDto);
        return clientDto;
    }


    @Override
    public ClientDto updateClient(Long id, ClientDto clientDto) {

        Client client = this.clientRepository.findById(id).get();
        if (client == null) {
            throw new RuntimeException("No client was found!");
        } else {
            client.setIsActive(clientDto.getIsActive());
            client.setAge(clientDto.getAge());
            client.setFullName(clientDto.getFullName());
            client.setPhone(clientDto.getPhone());
            client.setSexe(clientDto.getSexe());
            client.setEmail(clientDto.getEmail());
            Client client1 = this.clientRepository.save(client);
            ClientDto clientDt = new ClientDto();
            BeanUtils.copyProperties(client1, clientDt);
            return clientDt;
        }
    }

    @Override
    public boolean deleteClient(Long id) {
        Client client = this.clientRepository.findClientById(id);
           client.setIsActive(false);
           clientRepository.save(client);
         return true;
    }

    @Override
    public List<ClientDto> getAllClients(int page,int limit) {

        Pageable pageable = PageRequest.of(page,limit);
        Page<Client> clientDtoPage = clientRepository.findAll(pageable);
        List<Client> clients = clientDtoPage.getContent();
        Type listType = (new TypeToken<List<ClientDto>>() {
        }).getType();
        List<ClientDto> listDtos = (List)(new ModelMapper()).map(clients, listType);
        return listDtos;
    }

    @Override
    public List<ClientDto> getAllClientsBySexe(sexe sexe) {
        List<Client> clients = this.clientRepository.findAllBySexe(sexe);
        Type listType = (new TypeToken<List<ClientDto>>() {
        }).getType();
        List<ClientDto> clientDtos = (List)(new ModelMapper()).map(clients, listType);
        return clientDtos;
    }

    @Override
    public List<ClientDto> saveClients(List<Client> clients) {
        try {
            String regexEmail = "^[A-Za-z0-9+_.-]+@(.+)$";

            Pattern patternEmail = Pattern.compile(regexEmail);

            for(Client c : clients){
                Matcher matcherEmail = patternEmail.matcher(c.getEmail());
                if(!matcherEmail.matches() ){
                    System.out.println("Email is not valid :  ");
                    return null;
                }
            }
            List<Client> ListClients = clientRepository.saveAll(clients);
            Type listType = (new TypeToken<List<ClientDto>>() {
            }).getType();
            List<ClientDto> listDtos = (List)(new ModelMapper()).map(ListClients, listType);
            return listDtos;

        }catch (IllegalStateException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}

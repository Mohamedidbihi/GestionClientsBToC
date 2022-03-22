package com.app.gestionclients.repository;

import com.app.gestionclients.entity.Client;
import com.app.gestionclients.enums.sexe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {

    Client findClientById(Long id);

    Client findClientByEmailAndIsActiveIsTrue(String email);

    List<Client> findAllBySexe(sexe sexe);

}

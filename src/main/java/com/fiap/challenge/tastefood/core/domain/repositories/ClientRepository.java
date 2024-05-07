package com.fiap.challenge.tastefood.core.domain.repositories;

import com.fiap.challenge.tastefood.core.domain.entities.ClientEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<ClientEntity, Long> {

    Optional<ClientEntity> findByDocument(String document);

}

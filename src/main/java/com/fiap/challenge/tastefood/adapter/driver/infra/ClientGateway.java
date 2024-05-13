package com.fiap.challenge.tastefood.adapter.driver.infra;

import com.fiap.challenge.tastefood.core.domain.entity.ClientEntity;

import java.util.List;
import java.util.Optional;

public interface ClientGateway {

	Long create(ClientEntity clientEntity);

	List<ClientEntity> findAll();

	Optional<ClientEntity> findByDocument(String document);

}

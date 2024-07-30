package com.fiap.challenge.tastefood.core.gateways;

import com.fiap.challenge.tastefood.app.adapter.output.persistence.entity.CustomerEntity;

import java.util.List;
import java.util.Optional;

public interface CustomerGateway {

	CustomerEntity save(CustomerEntity customerEntity);

	Optional<CustomerEntity> findById(Long id);

	List<CustomerEntity> findAll();

	List<CustomerEntity> findByDocument(String document);

}

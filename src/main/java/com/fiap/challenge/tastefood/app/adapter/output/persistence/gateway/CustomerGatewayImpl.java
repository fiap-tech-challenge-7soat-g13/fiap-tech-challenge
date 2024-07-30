package com.fiap.challenge.tastefood.app.adapter.output.persistence.gateway;

import com.fiap.challenge.tastefood.app.adapter.output.persistence.entity.CustomerEntity;
import com.fiap.challenge.tastefood.app.adapter.output.persistence.repository.CustomerRepository;
import com.fiap.challenge.tastefood.core.gateways.CustomerGateway;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class CustomerGatewayImpl implements CustomerGateway {

	private final CustomerRepository repository;

	public CustomerEntity save(CustomerEntity customerEntity) {
		return repository.save(customerEntity);
	}

	public Optional<CustomerEntity> findById(Long id) {
		return repository.findById(id);
	}

	public List<CustomerEntity> findAll() {
		return repository.findAll();
	}

	public List<CustomerEntity> findByDocument(String document) {
		return repository.findByDocument(document);
	}

}

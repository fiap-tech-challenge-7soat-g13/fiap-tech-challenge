package com.fiap.challenge.tastefood.core.gateways;

import com.fiap.challenge.tastefood.core.domain.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerGateway {

	Customer save(Customer customer);

	Optional<Customer> findById(Long id);

	List<Customer> findAll();

	List<Customer> findByDocument(String document);

}

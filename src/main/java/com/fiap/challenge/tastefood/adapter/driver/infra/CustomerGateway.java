package com.fiap.challenge.tastefood.adapter.driver.infra;

import com.fiap.challenge.tastefood.core.domain.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerGateway {

	Long create(Customer customer);

	List<Customer> findAll();

	Optional<Customer> findByDocument(String document);

}

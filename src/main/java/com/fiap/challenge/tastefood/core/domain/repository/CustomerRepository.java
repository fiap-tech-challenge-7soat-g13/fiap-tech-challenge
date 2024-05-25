package com.fiap.challenge.tastefood.core.domain.repository;

import com.fiap.challenge.tastefood.core.domain.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    Customer save(Customer customer);

    Optional<Customer> findById(Long id);

    List<Customer> findAll();

    boolean existsByDocument(String document);

}

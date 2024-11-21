package com.fiap.challenge.tastefood.core.gateways;

import com.fiap.challenge.tastefood.core.domain.Customer;

import java.util.Optional;

public interface CustomerGateway {

    Optional<Customer> findById(Long id);

}

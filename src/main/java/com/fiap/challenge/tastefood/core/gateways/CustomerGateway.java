package com.fiap.challenge.tastefood.core.gateways;

import com.fiap.challenge.tastefood.core.domain.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerGateway {

    Optional<Customer> findById(UUID id);

}

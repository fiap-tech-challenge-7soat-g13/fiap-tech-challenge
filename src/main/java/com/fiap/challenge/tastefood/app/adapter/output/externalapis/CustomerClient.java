package com.fiap.challenge.tastefood.app.adapter.output.externalapis;

import com.fiap.challenge.tastefood.core.domain.Customer;

import java.util.Optional;

public interface CustomerClient {

    Optional<Customer> getCustomer(Long id);

}

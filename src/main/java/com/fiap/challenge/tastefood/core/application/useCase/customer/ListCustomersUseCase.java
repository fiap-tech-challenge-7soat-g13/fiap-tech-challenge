package com.fiap.challenge.tastefood.core.application.useCase.customer;

import com.fiap.challenge.tastefood.adapter.driver.infra.CustomerGateway;
import com.fiap.challenge.tastefood.core.domain.entity.Customer;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ListCustomersUseCase {

    private final CustomerGateway clientGateway;

    @Transactional
    public List<Customer> execute() {
        return clientGateway.findAll();
    }

}
package com.fiap.challenge.tastefood.core.useCases.customer;

import com.fiap.challenge.tastefood.core.domain.Customer;
import com.fiap.challenge.tastefood.core.gateways.CustomerGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerCreateAdminUseCase {

    private final CustomerGateway customerGateway;

    public Customer execute(String email, String password) {
        return customerGateway.saveAdmin(email, password);
    }
}
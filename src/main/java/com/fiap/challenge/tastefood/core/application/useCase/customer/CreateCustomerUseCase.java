package com.fiap.challenge.tastefood.core.application.useCase.customer;

import com.fiap.challenge.tastefood.adapter.driver.infra.CustomerGateway;
import com.fiap.challenge.tastefood.core.domain.entity.Customer;
import com.fiap.challenge.tastefood.core.domain.validation.CreateCustomerValidator;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class CreateCustomerUseCase {

    private final CreateCustomerValidator customerValidator;
    private final CustomerGateway customerGateway;

    @Transactional
    public Long execute(Customer customer) {
        customerValidator.validate(customer);
        return customerGateway.create(customer);
    }
}
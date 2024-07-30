package com.fiap.challenge.tastefood.core.useCases.customer;

import com.fiap.challenge.tastefood.app.adapter.output.persistence.entity.CustomerEntity;
import com.fiap.challenge.tastefood.app.adapter.output.persistence.mapper.CustomerMapper;
import com.fiap.challenge.tastefood.core.common.validator.CustomerCreateValidator;
import com.fiap.challenge.tastefood.core.domain.Customer;
import com.fiap.challenge.tastefood.core.gateways.CustomerGateway;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerCreateUseCase {

    private final CustomerMapper mapper;
    private final CustomerGateway customerGateway;
    private final CustomerCreateValidator validator;

    @Transactional
    public Long execute(Customer customer) {
        validator.validate(customer);

        CustomerEntity entity = mapper.toCustomerEntity(customer);

        CustomerEntity saved = customerGateway.save(entity);

        return saved.getId();
    }
}
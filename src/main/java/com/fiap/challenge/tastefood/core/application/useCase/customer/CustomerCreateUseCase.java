package com.fiap.challenge.tastefood.core.application.useCase.customer;

import com.fiap.challenge.tastefood.core.application.dto.CustomerRequest;
import com.fiap.challenge.tastefood.core.domain.entity.Customer;
import com.fiap.challenge.tastefood.core.application.mapper.CustomerRequestMapper;
import com.fiap.challenge.tastefood.core.domain.repository.CustomerRepository;
import com.fiap.challenge.tastefood.core.application.validator.CustomerCreateValidator;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerCreateUseCase {

    private final CustomerRequestMapper mapper;
    private final CustomerRepository repository;
    private final CustomerCreateValidator validator;

    @Transactional
    public Long execute(CustomerRequest customer) {

        validator.validate(customer);

        Customer entity = mapper.map(customer);

        Customer saved = repository.save(entity);

        return saved.getId();
    }
}
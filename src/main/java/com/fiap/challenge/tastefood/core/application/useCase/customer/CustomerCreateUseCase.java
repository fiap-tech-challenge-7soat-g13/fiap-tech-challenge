package com.fiap.challenge.tastefood.core.application.useCase.customer;

import com.fiap.challenge.tastefood.core.domain.entity.Customer;
import com.fiap.challenge.tastefood.core.domain.repository.CustomerRepository;
import com.fiap.challenge.tastefood.core.domain.validation.CustomerCreateValidator;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class CustomerCreateUseCase {

    private final CustomerRepository repository;
    private final CustomerCreateValidator validator;

    @Transactional
    public Long execute(Customer customer) {

        validator.validate(customer);

        Customer saved = repository.save(customer);

        return saved.getId();
    }
}
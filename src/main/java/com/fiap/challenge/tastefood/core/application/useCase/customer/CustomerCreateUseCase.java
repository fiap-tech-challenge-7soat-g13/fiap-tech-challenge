package com.fiap.challenge.tastefood.core.application.useCase.customer;

import com.fiap.challenge.tastefood.core.application.mapper.CustomerInputMapper;
import com.fiap.challenge.tastefood.core.application.validator.CustomerCreateValidator;
import com.fiap.challenge.tastefood.core.application.vo.CustomerInput;
import com.fiap.challenge.tastefood.core.domain.entity.Customer;
import com.fiap.challenge.tastefood.core.domain.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerCreateUseCase {

    private final CustomerInputMapper mapper;
    private final CustomerRepository repository;
    private final CustomerCreateValidator validator;

    @Transactional
    public Long execute(CustomerInput customerInput) {

        validator.validate(customerInput);

        Customer entity = mapper.toCustomer(customerInput);

        Customer saved = repository.save(entity);

        return saved.getId();
    }
}
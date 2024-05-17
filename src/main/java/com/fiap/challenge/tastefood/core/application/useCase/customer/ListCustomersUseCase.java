package com.fiap.challenge.tastefood.core.application.useCase.customer;

import com.fiap.challenge.tastefood.core.domain.entity.Customer;
import com.fiap.challenge.tastefood.core.domain.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ListCustomersUseCase {

    private final CustomerRepository customerRepository;

    @Transactional
    public List<Customer> execute() {
        return customerRepository.findAll();
    }

}
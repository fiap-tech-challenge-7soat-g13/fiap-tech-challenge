package com.fiap.challenge.tastefood.core.domain.gateway;

import com.fiap.challenge.tastefood.adapter.driver.infra.CustomerGateway;
import com.fiap.challenge.tastefood.core.domain.entity.Customer;
import com.fiap.challenge.tastefood.core.domain.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class CustomerGatewayImpl implements CustomerGateway {

    private final CustomerRepository customerRepository;

    public Long create(Customer customer) {
        Customer save = customerRepository.save(customer);
        return save.getId();
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Optional<Customer> findByDocument(String document) {
        return customerRepository.findByDocument(document);
    }

}

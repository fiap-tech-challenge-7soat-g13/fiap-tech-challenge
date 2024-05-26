package com.fiap.challenge.tastefood.core.application.mapper;

import com.fiap.challenge.tastefood.core.domain.entity.Customer;
import com.fiap.challenge.tastefood.core.domain.repository.CustomerRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class CustomerIdMapper {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer map(Long id) {
        if (id == null) {
            return null;
        }
        return customerRepository.findById(id).orElse(null);
    }

}

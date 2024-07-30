package com.fiap.challenge.tastefood.app.adapter.output.persistence.mapper;

import com.fiap.challenge.tastefood.app.adapter.output.persistence.entity.CustomerEntity;
import com.fiap.challenge.tastefood.app.adapter.output.persistence.repository.CustomerRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class CustomerIdMapper {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerEntity map(Long id) {
        if (id == null) {
            return null;
        }
        return customerRepository.findById(id).orElse(null);
    }

}

package com.fiap.challenge.tastefood.app.adapter.output.persistence.mapper;

import com.fiap.challenge.tastefood.app.adapter.output.persistence.entity.CustomerEntity;
import com.fiap.challenge.tastefood.core.domain.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerEntity toCustomerEntity(Customer customer);

    List<Customer> toCustomer(List<CustomerEntity> customerEntities);

}

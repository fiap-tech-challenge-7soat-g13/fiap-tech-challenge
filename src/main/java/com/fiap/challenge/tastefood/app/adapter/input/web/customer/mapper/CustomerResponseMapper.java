package com.fiap.challenge.tastefood.app.adapter.input.web.customer.mapper;

import com.fiap.challenge.tastefood.app.adapter.input.web.customer.dto.CustomerResponse;
import com.fiap.challenge.tastefood.core.domain.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerResponseMapper {

    List<CustomerResponse> toCustomer(List<Customer> customers);

}

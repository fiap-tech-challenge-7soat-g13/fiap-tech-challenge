package com.fiap.challenge.tastefood.app.adapter.input.web.customer.mapper;

import com.fiap.challenge.tastefood.app.adapter.input.web.customer.dto.CustomerRequest;
import com.fiap.challenge.tastefood.core.domain.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerRequestMapper {

    Customer toCustomer(CustomerRequest customerRequest);

}

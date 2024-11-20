package com.fiap.challenge.tastefood.app.adapter.output.externalapis.customer;

import com.fiap.challenge.tastefood.core.domain.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
interface GetCustomerResponseMapper {

    Customer toCustomer(GetCustomerResponse getCustomerResponse);

}

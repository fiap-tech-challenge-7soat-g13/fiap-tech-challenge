package com.fiap.challenge.tastefood.core.application.mapper;

import com.fiap.challenge.tastefood.core.application.dto.CustomerResponse;
import com.fiap.challenge.tastefood.core.domain.entity.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerResponseMapper {

    List<CustomerResponse> map(List<Customer> customers);

}

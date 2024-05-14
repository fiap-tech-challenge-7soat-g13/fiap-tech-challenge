package com.fiap.challenge.tastefood.core.domain.mapper;

import com.fiap.challenge.tastefood.core.application.dto.CustomerResponse;
import com.fiap.challenge.tastefood.core.domain.entity.Customer;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true), 
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
public interface CustomerResponseMapper {

    CustomerResponse map(Customer customer);

    List<CustomerResponse> map(List<Customer> customers);

}

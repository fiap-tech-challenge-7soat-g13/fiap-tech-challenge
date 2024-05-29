package com.fiap.challenge.tastefood.adapter.driver.mapper;

import com.fiap.challenge.tastefood.adapter.driver.dto.CustomerRequest;
import com.fiap.challenge.tastefood.adapter.driver.dto.CustomerResponse;
import com.fiap.challenge.tastefood.core.application.vo.CustomerInput;
import com.fiap.challenge.tastefood.core.application.vo.CustomerOutput;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerInput toCustomerInput(CustomerRequest customerRequest);

    List<CustomerResponse> toCustomerResponse(List<CustomerOutput> customersOutput);

}

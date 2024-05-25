package com.fiap.challenge.tastefood.core.domain.mapper;

import com.fiap.challenge.tastefood.core.application.dto.CustomerRequest;
import com.fiap.challenge.tastefood.core.domain.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerRequestMapper {

    Customer map(CustomerRequest customer);

}

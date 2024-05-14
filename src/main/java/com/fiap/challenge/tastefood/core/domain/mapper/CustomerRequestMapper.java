package com.fiap.challenge.tastefood.core.domain.mapper;

import com.fiap.challenge.tastefood.adapter.driver.dto.CustomerRequest;
import com.fiap.challenge.tastefood.core.domain.entity.Customer;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true), 
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
public interface CustomerRequestMapper {
    
    Customer map(CustomerRequest customer);

}

package com.fiap.challenge.tastefood.core.application.mapper;

import com.fiap.challenge.tastefood.core.application.vo.CustomerInput;
import com.fiap.challenge.tastefood.core.domain.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerInputMapper {

    Customer toCustomer(CustomerInput customerInput);

}

package com.fiap.challenge.tastefood.core.application.mapper;

import com.fiap.challenge.tastefood.core.application.vo.CustomerOutput;
import com.fiap.challenge.tastefood.core.domain.entity.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerOutputMapper {

    List<CustomerOutput> map(List<Customer> customers);

}

package com.fiap.challenge.tastefood.core.application.mapper;

import com.fiap.challenge.tastefood.core.application.vo.OrderInput;
import com.fiap.challenge.tastefood.core.domain.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CustomerIdMapper.class, OrderProductInputMapper.class})
public interface OrderInputMapper {

    @Mapping(source = "customerId", target = "customer")
    Order map(OrderInput order);

}

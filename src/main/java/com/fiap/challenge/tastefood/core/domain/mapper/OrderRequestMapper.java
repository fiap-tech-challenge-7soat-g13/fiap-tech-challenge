package com.fiap.challenge.tastefood.core.domain.mapper;

import com.fiap.challenge.tastefood.core.application.dto.OrderRequest;
import com.fiap.challenge.tastefood.core.domain.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CustomerIdMapper.class, OrderProductRequestMapper.class})
public interface OrderRequestMapper {

    @Mapping(source = "customerId", target = "customer")
    Order map(OrderRequest order);

}

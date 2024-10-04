package com.fiap.challenge.tastefood.app.adapter.input.web.order.mapper;

import com.fiap.challenge.tastefood.app.adapter.input.web.order.dto.OrderRequest;
import com.fiap.challenge.tastefood.core.domain.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {OrderProductRequestMapper.class})
public interface OrderRequestMapper {

    @Mapping(source = "customerId", target = "customer.id")
    Order toOrder(OrderRequest orderRequest);

}

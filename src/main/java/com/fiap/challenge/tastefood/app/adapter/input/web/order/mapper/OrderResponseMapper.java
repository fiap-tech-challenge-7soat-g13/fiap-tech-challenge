package com.fiap.challenge.tastefood.app.adapter.input.web.order.mapper;

import com.fiap.challenge.tastefood.app.adapter.input.web.order.dto.OrderResponse;
import com.fiap.challenge.tastefood.core.domain.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderResponseMapper {

    OrderResponse toOrderResponse(Order order);

    List<OrderResponse> toOrderResponse(List<Order> orders);

}

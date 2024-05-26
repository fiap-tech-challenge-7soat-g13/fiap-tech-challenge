package com.fiap.challenge.tastefood.core.application.mapper;

import com.fiap.challenge.tastefood.core.application.dto.OrderResponse;
import com.fiap.challenge.tastefood.core.domain.entity.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderResponseMapper {

    List<OrderResponse> map(List<Order> orders);

}

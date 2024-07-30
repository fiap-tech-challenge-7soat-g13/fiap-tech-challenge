package com.fiap.challenge.tastefood.app.adapter.output.persistence.mapper;

import com.fiap.challenge.tastefood.app.adapter.output.persistence.entity.OrderEntity;
import com.fiap.challenge.tastefood.core.domain.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CustomerIdMapper.class, OrderProductMapper.class})
public interface OrderMapper {

    OrderEntity toOrderEntity(Order order);

    Order toOrder(OrderEntity orderEntity);

    List<Order> toOrder(List<OrderEntity> orderEntities);

}

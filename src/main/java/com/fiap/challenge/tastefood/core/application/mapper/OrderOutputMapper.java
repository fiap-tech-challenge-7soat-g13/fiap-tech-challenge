package com.fiap.challenge.tastefood.core.application.mapper;

import com.fiap.challenge.tastefood.core.application.vo.OrderOutput;
import com.fiap.challenge.tastefood.core.domain.entity.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderOutputMapper {

    OrderOutput toOrderOutput(Order order);

    List<OrderOutput> toOrderOutput(List<Order> orders);

}

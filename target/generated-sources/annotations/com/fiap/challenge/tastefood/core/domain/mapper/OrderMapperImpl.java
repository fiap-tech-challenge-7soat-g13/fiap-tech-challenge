package com.fiap.challenge.tastefood.core.domain.mapper;

import com.fiap.challenge.tastefood.adapter.driver.formsDto.OrderFormDto;
import com.fiap.challenge.tastefood.core.applications.dtos.Order;
import com.fiap.challenge.tastefood.core.domain.entities.OrderEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-07T01:36:09-0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 21 (Azul Systems, Inc.)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderEntity toOrderEntity(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderEntity orderEntity = new OrderEntity();

        return orderEntity;
    }

    @Override
    public Order fromOrderEntity(OrderEntity orderEntity) {
        if ( orderEntity == null ) {
            return null;
        }

        Order order = new Order();

        return order;
    }

    @Override
    public Order fromOrderFormDto(OrderFormDto orderFormDto) {
        if ( orderFormDto == null ) {
            return null;
        }

        Order order = new Order();

        return order;
    }

    @Override
    public Order fromOrderEntityToOrderDTO(OrderEntity orderEntity) {
        if ( orderEntity == null ) {
            return null;
        }

        Order order = new Order();

        return order;
    }
}

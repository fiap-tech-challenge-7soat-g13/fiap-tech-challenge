package com.fiap.challenge.tastefood.core.domain.mapper;

import com.fiap.challenge.tastefood.adapter.driver.formsDto.OrderFormDto;
import com.fiap.challenge.tastefood.core.applications.dtos.Order;
import com.fiap.challenge.tastefood.core.domain.entities.OrderEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true), 
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
public interface OrderMapper {
    
    OrderEntity toOrderEntity(Order order);

    Order fromOrderEntity(OrderEntity orderEntity);

    Order fromOrderFormDto(OrderFormDto orderFormDto);

    Order fromOrderEntityToOrderDTO(OrderEntity orderEntity);

}

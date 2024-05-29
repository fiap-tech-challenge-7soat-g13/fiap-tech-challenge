package com.fiap.challenge.tastefood.adapter.driver.mapper;

import com.fiap.challenge.tastefood.adapter.driver.dto.OrderRequest;
import com.fiap.challenge.tastefood.adapter.driver.dto.OrderResponse;
import com.fiap.challenge.tastefood.core.application.vo.OrderInput;
import com.fiap.challenge.tastefood.core.application.vo.OrderOutput;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderInput toOrderInput(OrderRequest orderRequest);

    OrderResponse toOrderResponse(OrderOutput orderOutput);

    List<OrderResponse> toOrderResponse(List<OrderOutput> ordersOutput);

}

package com.fiap.challenge.tastefood.adapter.driver.controller;

import com.fiap.challenge.tastefood.adapter.driver.dto.OrderRequest;
import com.fiap.challenge.tastefood.core.application.dto.OrderResponse;
import com.fiap.challenge.tastefood.core.application.useCase.order.CreateOrderUseCase;
import com.fiap.challenge.tastefood.core.application.useCase.order.ListOrdersUseCase;
import com.fiap.challenge.tastefood.core.application.useCase.order.UpdateStatusOrderUseCase;
import com.fiap.challenge.tastefood.core.domain.entity.OrderStatusEnum;
import com.fiap.challenge.tastefood.core.domain.mapper.OrderRequestMapper;
import com.fiap.challenge.tastefood.core.domain.mapper.OrderResponseMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;
    private final ListOrdersUseCase listOrdersUseCase;
    private final UpdateStatusOrderUseCase updateStatusOrderUseCase;
    private final OrderRequestMapper orderRequestMapper;
    private final OrderResponseMapper orderResponseMapper;

    @PostMapping(path = "/order")
    public void create(@RequestBody OrderRequest request) {
        createOrderUseCase.execute(orderRequestMapper.map(request));
    }

    @GetMapping(path = "/order")
    public List<OrderResponse> list(@RequestParam(required = false) OrderStatusEnum status) {
        return orderResponseMapper.map(listOrdersUseCase.execute(status));
    }

    @PostMapping(path = "/order/{id}/status")
    public void updateStatus(@PathVariable Long id, @RequestParam OrderStatusEnum status) {
        updateStatusOrderUseCase.execute(id, status);
    }

}

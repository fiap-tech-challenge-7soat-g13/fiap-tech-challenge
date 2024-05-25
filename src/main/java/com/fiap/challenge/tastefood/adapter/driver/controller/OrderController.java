package com.fiap.challenge.tastefood.adapter.driver.controller;

import com.fiap.challenge.tastefood.core.application.dto.OrderRequest;
import com.fiap.challenge.tastefood.core.application.dto.OrderResponse;
import com.fiap.challenge.tastefood.core.application.useCase.order.OrderCreateUseCase;
import com.fiap.challenge.tastefood.core.application.useCase.order.OrderListUseCase;
import com.fiap.challenge.tastefood.core.application.useCase.order.OrderUpdateStatusUseCase;
import com.fiap.challenge.tastefood.core.domain.valueObject.OrderStatus;
import com.fiap.challenge.tastefood.core.domain.mapper.OrderRequestMapper;
import com.fiap.challenge.tastefood.core.domain.mapper.OrderResponseMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class OrderController {

    private final OrderCreateUseCase orderCreateUseCase;
    private final OrderListUseCase orderListUseCase;
    private final OrderUpdateStatusUseCase orderUpdateStatusUseCase;
    private final OrderRequestMapper orderRequestMapper;
    private final OrderResponseMapper orderResponseMapper;

    @PostMapping(path = "/order")
    public void create(@RequestBody OrderRequest request) {
        orderCreateUseCase.execute(orderRequestMapper.map(request));
    }

    @GetMapping(path = "/order")
    public List<OrderResponse> list(@RequestParam(required = false) OrderStatus status) {
        return orderResponseMapper.map(orderListUseCase.execute(status));
    }

    @PostMapping(path = "/order/{id}/status")
    public void updateStatus(@PathVariable Long id, @RequestParam OrderStatus status) {
        orderUpdateStatusUseCase.execute(id, status);
    }

}

package com.fiap.challenge.tastefood.adapter.driver.controller;

import com.fiap.challenge.tastefood.adapter.driver.dto.OrderRequest;
import com.fiap.challenge.tastefood.adapter.driver.dto.OrderResponse;
import com.fiap.challenge.tastefood.adapter.driver.mapper.OrderMapper;
import com.fiap.challenge.tastefood.core.application.useCase.order.*;
import com.fiap.challenge.tastefood.core.application.useCase.status.StatusListUseCase;
import com.fiap.challenge.tastefood.core.application.vo.OrderInput;
import com.fiap.challenge.tastefood.core.application.vo.OrderOutput;
import com.fiap.challenge.tastefood.core.domain.valueObject.OrderStatus;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RestController
@AllArgsConstructor
public class OrderController {

    private final OrderCreateUseCase orderCreateUseCase;
    private final OrderListUseCase orderListUseCase;
    private final OrderUpdateStatusUseCase orderUpdateStatusUseCase;
    private final StatusListUseCase statusListUseCase;
    private final CheckoutOrderUseCase checkoutOrderUseCase;
    private final OrderQueueListUseCase orderQueueListUseCase;
    private final OrderMapper orderMapper;

    @PostMapping(path = "/order")
    public void create(@RequestBody OrderRequest orderRequest) {
        OrderInput orderInput = orderMapper.toOrderInput(orderRequest);
        orderCreateUseCase.execute(orderInput);
    }

    @GetMapping(path = "/order")
    public List<OrderResponse> list(@RequestParam(required = false) OrderStatus status) {
        List<OrderOutput> ordersOutput = orderListUseCase.execute(status);
        return orderMapper.toOrderResponse(ordersOutput);
    }

    @PutMapping(path = "/order/{id}/status")
    public void updateStatus(@PathVariable Long id, @RequestParam OrderStatus status) {
        orderUpdateStatusUseCase.execute(id, status);
    }

    @GetMapping(path = "/order/status")
    public ResponseEntity<?> list() {
        return ResponseEntity
                .status(OK)
                .body(statusListUseCase.execute());
    }

    @PostMapping(path = "/order/checkout/{id}")
    public ResponseEntity<?> checkout(@PathVariable Long id) {
        try {
            OrderOutput orderOutput = checkoutOrderUseCase.execute(id);
            return ResponseEntity
                    .status(OK)
                    .body(orderMapper.toOrderResponse(orderOutput));
        } catch (Exception e) {
            return ResponseEntity
                    .status(NOT_FOUND)
                    .body("Error: " + e.getMessage());
        }
    }

    @GetMapping(path = "/order/queue")
    public ResponseEntity<?> ordersQueue() {
        List<OrderOutput> ordersOutput = orderQueueListUseCase.execute(List.of(OrderStatus.RECEBIDO.name(), OrderStatus.EM_PREPARACAO.name()));
        return ResponseEntity
                .status(OK)
                .body(orderMapper.toOrderResponse(ordersOutput));
    }

}

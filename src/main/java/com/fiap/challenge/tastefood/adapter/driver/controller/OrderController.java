package com.fiap.challenge.tastefood.adapter.driver.controller;

import com.fiap.challenge.tastefood.core.application.dto.OrderRequest;
import com.fiap.challenge.tastefood.core.application.dto.OrderResponse;
import com.fiap.challenge.tastefood.core.application.useCase.order.*;
import com.fiap.challenge.tastefood.core.application.useCase.status.StatusListUseCase;
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

    @PostMapping(path = "/order")
    public void create(@RequestBody OrderRequest order) {
        orderCreateUseCase.execute(order);
    }

    @GetMapping(path = "/order")
    public List<OrderResponse> list(@RequestParam(required = false) OrderStatus status) {
        return orderListUseCase.execute(status);
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
            return ResponseEntity
                    .status(OK)
                    .body(checkoutOrderUseCase.execute(id));
        } catch (Exception e) {
            return ResponseEntity
                    .status(NOT_FOUND)
                    .body("Error: " + e.getMessage());
        }
    }

    @GetMapping(path = "/order/queue")
    public ResponseEntity<?> ordersQueue() {
        return ResponseEntity
                .status(OK)
                .body(orderQueueListUseCase.execute(List.of(OrderStatus.RECEBIDO.name(), OrderStatus.EM_PREPARACAO.name())));
    }

}

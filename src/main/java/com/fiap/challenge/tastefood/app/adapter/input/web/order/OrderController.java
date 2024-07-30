package com.fiap.challenge.tastefood.app.adapter.input.web.order;

import com.fiap.challenge.tastefood.app.adapter.input.web.order.mapper.OrderRequestMapper;
import com.fiap.challenge.tastefood.app.adapter.input.web.order.mapper.OrderResponseMapper;
import com.fiap.challenge.tastefood.app.adapter.input.web.product.dto.OrderRequest;
import com.fiap.challenge.tastefood.app.adapter.input.web.product.dto.OrderResponse;
import com.fiap.challenge.tastefood.core.domain.Order;
import com.fiap.challenge.tastefood.core.domain.enums.OrderStatus;
import com.fiap.challenge.tastefood.core.useCases.order.*;
import com.fiap.challenge.tastefood.core.useCases.status.StatusListUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@AllArgsConstructor
public class OrderController {

    private final OrderCreateUseCase orderCreateUseCase;
    private final OrderGetUseCase orderGetUseCase;
    private final OrderListUseCase orderListUseCase;
    private final OrderUpdateStatusUseCase orderUpdateStatusUseCase;
    private final StatusListUseCase statusListUseCase;
    private final OrderCheckoutUseCase orderCheckoutUseCase;
    private final OrderQueueListUseCase orderQueueListUseCase;
    private final OrderRequestMapper orderRequestMapper;
    private final OrderResponseMapper orderResponseMapper;

    @PostMapping(path = "/order")
    public ResponseEntity<OrderResponse> create(@RequestBody OrderRequest orderRequest) {
        Order orderInput = orderRequestMapper.toOrder(orderRequest);
        Order orderSaved = orderCreateUseCase.execute(orderInput);
        return ResponseEntity
                .status(CREATED)
                .body(orderResponseMapper.toOrderResponse(orderSaved));
    }

    @GetMapping(path = "/order/{id}")
    public OrderResponse get(@PathVariable Long id) {
        Order order = orderGetUseCase.execute(id);
        return orderResponseMapper.toOrderResponse(order);
    }

    @GetMapping(path = "/order")
    public List<OrderResponse> list(@RequestParam(required = false) OrderStatus status) {
        List<Order> orders = orderListUseCase.execute(status);
        return orderResponseMapper.toOrderResponse(orders);
    }

    @PutMapping(path = "/order/{id}/status")
    public void updateStatus(@PathVariable Long id, @RequestParam OrderStatus status) {
        orderUpdateStatusUseCase.execute(id, status);
    }

    @GetMapping(path = "/order/status")
    public ResponseEntity<List<String>> list() {
        return ResponseEntity
                .status(OK)
                .body(statusListUseCase.execute());
    }

    @PostMapping(path = "/order/checkout/{id}")
    public ResponseEntity<?> checkout(@PathVariable Long id) {
        try {
            Order order = orderCheckoutUseCase.execute(id);
            return ResponseEntity
                    .status(OK)
                    .body(orderResponseMapper.toOrderResponse(order));
        } catch (Exception e) {
            return ResponseEntity
                    .status(NOT_FOUND)
                    .body("Error: " + e.getMessage());
        }
    }

    @GetMapping(path = "/order/queue")
    public ResponseEntity<List<OrderResponse>> ordersQueue() {
        List<Order> orders = orderQueueListUseCase.execute(List.of(OrderStatus.RECEBIDO.name(), OrderStatus.EM_PREPARACAO.name()));
        return ResponseEntity
                .status(OK)
                .body(orderResponseMapper.toOrderResponse(orders));
    }

}

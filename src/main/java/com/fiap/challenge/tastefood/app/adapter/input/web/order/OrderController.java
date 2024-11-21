package com.fiap.challenge.tastefood.app.adapter.input.web.order;

import com.fiap.challenge.tastefood.app.adapter.input.web.order.dto.OrderRequest;
import com.fiap.challenge.tastefood.app.adapter.input.web.order.dto.OrderResponse;
import com.fiap.challenge.tastefood.app.adapter.input.web.order.mapper.OrderRequestMapper;
import com.fiap.challenge.tastefood.app.adapter.input.web.order.mapper.OrderResponseMapper;
import com.fiap.challenge.tastefood.core.domain.Order;
import com.fiap.challenge.tastefood.core.domain.enums.OrderStatus;
import com.fiap.challenge.tastefood.core.usecases.order.*;
import com.fiap.challenge.tastefood.core.usecases.status.StatusListUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse create(@RequestBody OrderRequest orderRequest) {
        Order orderInput = orderRequestMapper.toOrder(orderRequest);
        Order orderSaved = orderCreateUseCase.execute(orderInput);
        return orderResponseMapper.toOrderResponse(orderSaved);
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
    public OrderResponse updateStatus(@PathVariable Long id, @RequestParam OrderStatus status) {
        Order order = orderUpdateStatusUseCase.execute(id, status);
        return orderResponseMapper.toOrderResponse(order);
    }

    @GetMapping(path = "/order/status")
    public List<String> list() {
        return statusListUseCase.execute();
    }

    @PostMapping(path = "/order/{id}/checkout")
    public OrderResponse checkout(@PathVariable Long id) {
        Order order = orderCheckoutUseCase.execute(id);
        return orderResponseMapper.toOrderResponse(order);
    }

    @GetMapping(path = "/order/queue")
    public List<OrderResponse> ordersQueue() {
        List<OrderStatus> statuses = List.of(OrderStatus.RECEBIDO, OrderStatus.EM_PREPARACAO, OrderStatus.PRONTO);
        List<Order> orders = orderQueueListUseCase.execute(statuses);
        return orderResponseMapper.toOrderResponse(orders);
    }

}

package com.fiap.challenge.tastefood.adapter.driver.controllers;

import com.fiap.challenge.tastefood.adapter.driver.formsDto.CheckoutOrderFormDto;
import com.fiap.challenge.tastefood.adapter.driver.formsDto.OrderFormDto;
import com.fiap.challenge.tastefood.adapter.driver.formsDto.UpdateStatusOrderFormDto;
import com.fiap.challenge.tastefood.core.applications.dtos.Checkout;
import com.fiap.challenge.tastefood.core.applications.dtos.Order;
import com.fiap.challenge.tastefood.core.applications.dtos.StatusOrderEnum;
import com.fiap.challenge.tastefood.core.applications.useCases.order.*;
import com.fiap.challenge.tastefood.core.domain.exception.OrderException;
import com.fiap.challenge.tastefood.core.domain.mapper.CheckoutMapper;
import com.fiap.challenge.tastefood.core.domain.mapper.OrderMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
public class OrderController {

    private final CheckoutOrderUseCase checkoutOrderUseCase;
    private final CreateOrderUseCase createOrderUseCase;
    private final AddItemIntoOrderUseCase addItemIntoOrderUseCase;
    private final GetAllOrdersUseCase getAllOrdersUseCase;
    private final GetAllOrdersByStatusUseCase getAllOrdersByStatusUseCase;
    private final UpdateStatusOrderUseCase updateStatusOrderUseCase;
    private final CheckoutMapper checkoutMapper;
    private final OrderMapper orderMapper;

    @GetMapping(path = "/order")
    @Transactional
    public ResponseEntity<List<Order>> findAll() {
        List<Order> orders = getAllOrdersUseCase.execute();
        if (orders.isEmpty())
            return new ResponseEntity<>(orders, HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping(path = "/order/create")
    @Transactional
    public ResponseEntity<Object> create(@RequestBody OrderFormDto orderFormDto) {
	    try {
            Long orderId = createOrderUseCase.execute(this.orderMapper.fromOrderFormDto(orderFormDto));
            if (orderId != null) {
                return ResponseEntity.ok(orderId);
            } else {
                log.info("Pedido não cadastrado!");
                return ResponseEntity.badRequest().body("Pedido não cadastrado!");
            }
	    } catch (OrderException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
	    }
    }

    @PostMapping(path = "/order/add/ingredient/{orderId}/{ingredientId}")
    @Transactional
    public ResponseEntity<?> addIngredient(@PathVariable Long orderId, @PathVariable Long ingredientId) {
        try {
            return ResponseEntity.ok(addItemIntoOrderUseCase.execute(orderId, ingredientId));
        } catch (OrderException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(path = "/order/checkout")
    @Transactional
    public ResponseEntity<Checkout> checkout(@RequestBody CheckoutOrderFormDto resumeOrder) {
        Checkout checkout = this.checkoutMapper.fromCheckoutOrderFormDto(resumeOrder);
        return ResponseEntity.ok(checkoutOrderUseCase.execute(checkout));
    }

    @GetMapping(path = "/order/status/{status}")
    @Transactional
    public ResponseEntity<?> listAllByStatusOrder(@PathVariable String status) {
        List<Order> orders = getAllOrdersByStatusUseCase.execute(status);
        if (orders.isEmpty())
            return new ResponseEntity<>(orders, HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping(path = "/order/status/update")
    @Transactional
    public ResponseEntity<?> statusUpdate(@RequestBody UpdateStatusOrderFormDto updateStatusOrderFormDto) {
        try {
            Order order = Order.builder()
                    .id(updateStatusOrderFormDto.getIdOrder())
                    .status(StatusOrderEnum.getStatusOrderEnum(updateStatusOrderFormDto.getStatusOrder()))
                    .build();
            return ResponseEntity.ok(updateStatusOrderUseCase.execute(order));
        } catch (OrderException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}

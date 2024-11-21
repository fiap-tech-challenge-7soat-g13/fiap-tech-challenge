package com.fiap.challenge.tastefood.app.adapter.output.persistence.gateway;

import com.fiap.challenge.tastefood.app.adapter.output.persistence.entity.OrderEntity;
import com.fiap.challenge.tastefood.app.adapter.output.persistence.mapper.OrderMapper;
import com.fiap.challenge.tastefood.app.adapter.output.persistence.repository.OrderRepository;
import com.fiap.challenge.tastefood.core.domain.Order;
import com.fiap.challenge.tastefood.core.domain.enums.OrderStatus;
import com.fiap.challenge.tastefood.core.gateways.CustomerGateway;
import com.fiap.challenge.tastefood.core.gateways.OrderGateway;
import com.fiap.challenge.tastefood.core.gateways.PaymentGateway;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class OrderGatewayImpl implements OrderGateway {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final PaymentGateway paymentGateway;
    private final CustomerGateway customerGateway;

    @Transactional
    public Order save(Order order) {
        OrderEntity orderEntity = orderMapper.toOrderEntity(order);
        OrderEntity orderSave = orderRepository.save(orderEntity);
        return setFields(orderMapper.toOrder(orderSave));
    }

    @Transactional
    public Optional<Order> findById(Long id) {
        Optional<OrderEntity> orderEntity = orderRepository.findById(id);
        return setFields(orderEntity.map(orderMapper::toOrder));
    }

    @Transactional
    public List<Order> findAll() {
        List<OrderEntity> orderList = orderRepository.findAll();
        return setFields(orderMapper.toOrder(orderList));
    }

    @Transactional
    public List<Order> findByStatus(OrderStatus status) {
        List<OrderEntity> orderList = orderRepository.findByStatus(status);
        return setFields(orderMapper.toOrder(orderList));
    }

    @Transactional
    public List<Order> findAllByStatusInOrderByStatusDesc(List<OrderStatus> orderStatus) {
        List<OrderEntity> orderList = orderRepository.findAllByStatusInOrderByStatusDesc(orderStatus);
        return setFields(orderMapper.toOrder(orderList));
    }

    private List<Order> setFields(List<Order> orders) {
        return orders.stream().map(this::setFields).toList();
    }

    private Optional<Order> setFields(Optional<Order> order) {
        return order.map(this::setFields);
    }

    private Order setFields(Order order) {
        paymentGateway.findByOrderId(order.getId()).ifPresent(order::setPayment);
        customerGateway.findById(order.getCustomer().getId()).ifPresent(order::setCustomer);
        return order;
    }

}

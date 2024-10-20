package com.fiap.challenge.tastefood.app.adapter.output.persistence.gateway;

import com.fiap.challenge.tastefood.app.adapter.output.persistence.entity.OrderEntity;
import com.fiap.challenge.tastefood.app.adapter.output.persistence.entity.PaymentEntity;
import com.fiap.challenge.tastefood.app.adapter.output.persistence.mapper.OrderMapper;
import com.fiap.challenge.tastefood.app.adapter.output.persistence.mapper.PaymentMapper;
import com.fiap.challenge.tastefood.app.adapter.output.persistence.repository.OrderRepository;
import com.fiap.challenge.tastefood.app.adapter.output.persistence.repository.PaymentRepository;
import com.fiap.challenge.tastefood.core.domain.Order;
import com.fiap.challenge.tastefood.core.domain.enums.OrderStatus;
import com.fiap.challenge.tastefood.core.gateways.OrderGateway;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class OrderGatewayImpl implements OrderGateway {

    private final OrderMapper orderMapper;
    private final PaymentMapper paymentMapper;
    private final OrderRepository repository;
    private final PaymentRepository paymentRepository;

    @Transactional
    public Order save(Order order) {
        OrderEntity orderEntity = orderMapper.toOrderEntity(order);
        OrderEntity orderSave = repository.save(orderEntity);
        return setPayment(orderMapper.toOrder(orderSave));
    }

    @Transactional
    public Optional<Order> findById(Long id) {
        Optional<OrderEntity> orderEntity = repository.findById(id);
        return setPayment(orderEntity.map(orderMapper::toOrder));
    }

    @Transactional
    public List<Order> findAll() {
        List<OrderEntity> orderList = repository.findAll();
        return setPayment(orderMapper.toOrder(orderList));
    }

    @Transactional
    public List<Order> findByStatus(OrderStatus status) {
        List<OrderEntity> orderList = repository.findByStatus(status);
        return setPayment(orderMapper.toOrder(orderList));
    }

    @Transactional
    public List<Order> findAllByStatusInOrderByStatusDesc(List<String> orderStatus) {
        List<OrderEntity> orderList = repository.findAllByStatusInOrderByStatusDesc(orderStatus);
        return setPayment(orderMapper.toOrder(orderList));
    }

    private List<Order> setPayment(List<Order> orders) {
        return orders.stream().map(this::setPayment).toList();
    }

    private Optional<Order> setPayment(Optional<Order> order) {
        return order.map(this::setPayment);
    }

    private Order setPayment(Order order) {
        Optional<PaymentEntity> payment = paymentRepository.findByOrderId(order.getId());
        payment.map(paymentMapper::toPayment).ifPresent(order::setPayment);
        return order;
    }

}

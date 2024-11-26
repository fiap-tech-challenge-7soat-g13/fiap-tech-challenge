package com.fiap.challenge.tastefood.app.adapter.output.persistence.gateway;

import com.fiap.challenge.tastefood.app.adapter.output.persistence.entity.OrderEntity;
import com.fiap.challenge.tastefood.app.adapter.output.persistence.mapper.OrderMapper;
import com.fiap.challenge.tastefood.app.adapter.output.persistence.repository.OrderRepository;
import com.fiap.challenge.tastefood.core.domain.Customer;
import com.fiap.challenge.tastefood.core.domain.Order;
import com.fiap.challenge.tastefood.core.domain.Payment;
import com.fiap.challenge.tastefood.core.domain.enums.OrderStatus;
import com.fiap.challenge.tastefood.core.gateways.CustomerGateway;
import com.fiap.challenge.tastefood.core.gateways.PaymentGateway;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OrderGatewayImplTest {

    private final OrderMapper orderMapper = mock(OrderMapper.class);
    private final OrderRepository orderRepository = mock(OrderRepository.class);
    private final PaymentGateway paymentGateway = mock(PaymentGateway.class);
    private final CustomerGateway customerGateway = mock(CustomerGateway.class);

    private OrderGatewayImpl orderGateway = new OrderGatewayImpl(orderMapper, orderRepository, paymentGateway, customerGateway);

    @Test
    void shouldSave() {

        Order order = new Order();

        OrderEntity orderEntity = new OrderEntity();
        OrderEntity savedOrderEntity = new OrderEntity();
        Order expected = buildOrderWithCustomerId();

        when(orderMapper.toOrderEntity(order)).thenReturn(orderEntity);
        when(orderRepository.save(orderEntity)).thenReturn(savedOrderEntity);
        when(orderMapper.toOrder(savedOrderEntity)).thenReturn(expected);

        Order actual = orderGateway.save(order);

        verify(orderMapper).toOrderEntity(order);
        verify(orderRepository).save(orderEntity);
        verify(orderMapper).toOrder(savedOrderEntity);

        assertEquals(expected, actual);
    }

    @Test
    void shouldFindById() {

        Long id = 1L;

        OrderEntity orderEntity = new OrderEntity();
        Order expected = buildOrderWithCustomerId();

        when(orderRepository.findById(id)).thenReturn(Optional.of(orderEntity));
        when(orderMapper.toOrder(orderEntity)).thenReturn(expected);

        Optional<Order> actual = orderGateway.findById(id);

        verify(orderRepository).findById(id);
        verify(orderMapper).toOrder(orderEntity);

        assertEquals(Optional.of(expected), actual);
    }

    @Test
    void shouldFindAll() {

        List<OrderEntity> orderEntityList = List.of(new OrderEntity(), new OrderEntity());
        List<Order> expected = List.of(buildOrderWithCustomerId(), buildOrderWithCustomerId());

        when(orderRepository.findAll()).thenReturn(orderEntityList);
        when(orderMapper.toOrder(orderEntityList)).thenReturn(expected);

        List<Order> actual = orderGateway.findAll();

        verify(orderRepository).findAll();
        verify(orderMapper).toOrder(orderEntityList);

        assertEquals(expected, actual);
    }

    @Test
    void shouldFindByStatus() {

        OrderStatus orderStatus = OrderStatus.CRIADO;

        List<OrderEntity> orderEntityList = List.of(new OrderEntity(), new OrderEntity());
        List<Order> expected = List.of(buildOrderWithCustomerId(), buildOrderWithCustomerId());

        when(orderRepository.findByStatus(orderStatus)).thenReturn(orderEntityList);
        when(orderMapper.toOrder(orderEntityList)).thenReturn(expected);

        List<Order> actual = orderGateway.findByStatus(orderStatus);

        verify(orderRepository).findByStatus(orderStatus);
        verify(orderMapper).toOrder(orderEntityList);

        assertEquals(expected, actual);
    }

    @Test
    void shouldFindByStatusList() {

        List<OrderStatus> orderStatusList = List.of(OrderStatus.CRIADO, OrderStatus.CANCELADO);

        List<OrderEntity> orderEntityList = List.of(new OrderEntity(), new OrderEntity());
        List<Order> expected = List.of(buildOrderWithCustomerId(), buildOrderWithCustomerId());

        when(orderRepository.findAllByStatusInOrderByStatusDesc(orderStatusList)).thenReturn(orderEntityList);
        when(orderMapper.toOrder(orderEntityList)).thenReturn(expected);

        List<Order> actual = orderGateway.findAllByStatusInOrderByStatusDesc(orderStatusList);

        verify(orderRepository).findAllByStatusInOrderByStatusDesc(orderStatusList);
        verify(orderMapper).toOrder(orderEntityList);

        assertEquals(expected, actual);
    }

    @Test
    void shouldFindByPaymentId() {

        Long paymentId = 1L;

        OrderEntity orderEntity = new OrderEntity();
        Order expected = buildOrderWithCustomerId();

        when(orderRepository.findByPaymentId(paymentId)).thenReturn(orderEntity);
        when(orderMapper.toOrder(orderEntity)).thenReturn(expected);

        Order actual = orderGateway.findByPaymentId(paymentId);

        verify(orderRepository).findByPaymentId(paymentId);
        verify(orderMapper).toOrder(orderEntity);

        assertEquals(expected, actual);
    }

    private Order buildOrderWithCustomerId() {
        Customer customer = new Customer();
        customer.setId(1L);
        Payment payment = new Payment();
        payment.setId(1L);
        Order order = new Order();
        order.setCustomer(customer);
        order.setPayment(payment);
        return order;
    }

}

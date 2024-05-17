package com.fiap.challenge.tastefood.core.application.useCase.order;

import com.fiap.challenge.tastefood.core.domain.entity.Order;
import com.fiap.challenge.tastefood.core.domain.entity.OrderProduct;
import com.fiap.challenge.tastefood.core.domain.entity.OrderStatusEnum;
import com.fiap.challenge.tastefood.core.domain.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CreateOrderUseCase {

    private final OrderRepository orderRepository;

    @Transactional
    public Long execute(Order order) {
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus(OrderStatusEnum.RECEBIDO);
        for (OrderProduct orderProduct : order.getProducts()) {
            orderProduct.setOrder(order);
            orderProduct.setPrice(orderProduct.getProduct().getPrice());
        }
        Order saved = orderRepository.save(order);
        return saved.getId();
    }

}

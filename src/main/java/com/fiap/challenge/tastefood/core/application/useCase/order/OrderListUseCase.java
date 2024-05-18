package com.fiap.challenge.tastefood.core.application.useCase.order;

import com.fiap.challenge.tastefood.core.domain.entity.Order;
import com.fiap.challenge.tastefood.core.domain.entity.OrderStatusEnum;
import com.fiap.challenge.tastefood.core.domain.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderListUseCase {

    private final OrderRepository orderRepository;

    @Transactional
    public List<Order> execute(OrderStatusEnum status) {
        return status == null ? orderRepository.findAll() : orderRepository.findByStatus(status);
    }

}

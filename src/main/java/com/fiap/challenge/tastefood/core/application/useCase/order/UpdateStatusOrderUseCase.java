package com.fiap.challenge.tastefood.core.application.useCase.order;

import com.fiap.challenge.tastefood.core.domain.entity.Order;
import com.fiap.challenge.tastefood.core.domain.entity.OrderStatusEnum;
import com.fiap.challenge.tastefood.core.domain.exception.EntityNotFoundException;
import com.fiap.challenge.tastefood.core.domain.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateStatusOrderUseCase {

    private final OrderRepository orderRepository;

    @Transactional
    public void execute(Long id, OrderStatusEnum status) {
        Order order = orderRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        OrderStatusEnum.isValidUpdateStatus(order.getStatus(), status);
        order.setStatus(status);
        orderRepository.save(order);
    }

}

package com.fiap.challenge.tastefood.core.application.useCase.order;

import com.fiap.challenge.tastefood.core.domain.entity.Order;
import com.fiap.challenge.tastefood.core.domain.entity.OrderStatusEnum;
import com.fiap.challenge.tastefood.core.domain.repository.OrderRepository;
import com.fiap.challenge.tastefood.core.domain.validation.UpdateStatusOrderValidator;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateStatusOrderUseCase {

    private final OrderRepository orderRepository;

    private final UpdateStatusOrderValidator validator;

    @Transactional
    public void execute(Long id, OrderStatusEnum status) {

        validator.validate(id, status);

        Order order = orderRepository.getReferenceById(id);
        order.setStatus(status);
        orderRepository.save(order);
    }

}

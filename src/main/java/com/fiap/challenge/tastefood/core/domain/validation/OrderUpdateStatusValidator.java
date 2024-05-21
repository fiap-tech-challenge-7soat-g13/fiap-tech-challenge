package com.fiap.challenge.tastefood.core.domain.validation;

import com.fiap.challenge.tastefood.core.domain.entity.Order;
import com.fiap.challenge.tastefood.core.domain.entity.OrderStatusEnum;
import com.fiap.challenge.tastefood.core.domain.exception.EntityNotFoundException;
import com.fiap.challenge.tastefood.core.domain.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderUpdateStatusValidator {

    private final OrderRepository orderRepository;

    public void validate(Long id, OrderStatusEnum status) {

        Order order = orderRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        Validator validator = new Validator();

        validator.add(Validation.assertTrue(order.getStatus().isPossibleStatusUpdate(status), "O novo status do pedido é inválido"));

        validator.assertEmptyMessages();
    }

}

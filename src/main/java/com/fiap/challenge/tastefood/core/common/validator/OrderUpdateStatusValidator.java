package com.fiap.challenge.tastefood.core.common.validator;

import com.fiap.challenge.tastefood.core.common.exception.EntityNotFoundException;
import com.fiap.challenge.tastefood.core.common.util.validation.Validation;
import com.fiap.challenge.tastefood.core.common.util.validation.Validator;
import com.fiap.challenge.tastefood.app.adapter.output.persistence.entity.OrderEntity;
import com.fiap.challenge.tastefood.app.adapter.output.persistence.repository.OrderRepository;
import com.fiap.challenge.tastefood.core.domain.enums.OrderStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderUpdateStatusValidator {

    private final OrderRepository orderRepository;

    public void validate(Long id, OrderStatus status) {

        OrderEntity orderEntity = orderRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        Validator validator = new Validator();

        validator.add(Validation.assertTrue(orderEntity.getStatus().isPossibleStatusUpdate(status), "O novo status do pedido é inválido"));

        validator.assertEmptyMessages();
    }

}

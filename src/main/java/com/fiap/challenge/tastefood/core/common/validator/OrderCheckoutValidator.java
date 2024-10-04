package com.fiap.challenge.tastefood.core.common.validator;

import com.fiap.challenge.tastefood.core.common.exception.EntityNotFoundException;
import com.fiap.challenge.tastefood.core.common.util.validation.Validation;
import com.fiap.challenge.tastefood.core.common.util.validation.Validator;
import com.fiap.challenge.tastefood.core.domain.Order;
import com.fiap.challenge.tastefood.core.domain.enums.OrderStatus;
import com.fiap.challenge.tastefood.core.gateways.OrderGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderCheckoutValidator {

    private final OrderGateway orderGateway;

    public void validate(Long id) {

        Order order = orderGateway.findById(id).orElseThrow(EntityNotFoundException::new);

        Validator validator = new Validator();

        validator.add(Validation.assertTrue(order.getStatus().equals(OrderStatus.CRIADO), "Só é possível realizar o checkout em pedidos com o status CRIADO"));

        validator.assertEmptyMessages();
    }

}

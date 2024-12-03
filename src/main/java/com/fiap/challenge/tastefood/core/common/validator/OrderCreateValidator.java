package com.fiap.challenge.tastefood.core.common.validator;

import com.fiap.challenge.tastefood.core.common.util.validation.Validation;
import com.fiap.challenge.tastefood.core.common.util.validation.Validator;
import com.fiap.challenge.tastefood.core.domain.Order;
import com.fiap.challenge.tastefood.core.domain.OrderProduct;
import com.fiap.challenge.tastefood.core.gateways.CustomerGateway;
import com.fiap.challenge.tastefood.core.gateways.ProductGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class OrderCreateValidator {

    private final CustomerGateway customerGateway;
    private final ProductGateway productGateway;

    public void validate(Order order) {

        Validator validator = new Validator();

        validator.add(Validation.assertFalse(customerNotFound(order.getCustomer().getId()), "Cliente com ID %s não encontrado", order.getCustomer().getId()));
        validator.add(Validation.notEmpty(order.getProducts(), "É obrigatório informar os produtos"));

        if (order.getProducts() != null) {
            for (OrderProduct orderProduct : order.getProducts()) {
                validator.add(Validation.notNull(orderProduct.getQuantity(), "É obrigatório informar a quantidade"));
                validator.add(Validation.greaterThan(orderProduct.getQuantity(), 0, "A quantidade deve ser maior que zero"));
                validator.add(Validation.notNull(orderProduct.getProduct().getId(), "É obrigatório informar o produto"));
                validator.add(Validation.assertFalse(productNotFound(orderProduct.getProduct().getId()), "Produto com ID %s não encontrado", orderProduct.getProduct().getId()));
            }
        }

        validator.assertEmptyMessages();
    }

    private boolean customerNotFound(UUID customerId) {
        return customerId != null && customerGateway.findById(customerId).isEmpty();
    }

    private boolean productNotFound(Long productId) {
        return productId != null && productGateway.findById(productId).isEmpty();
    }

}

package com.fiap.challenge.tastefood.core.domain.validation;

import com.fiap.challenge.tastefood.core.domain.entity.Order;
import com.fiap.challenge.tastefood.core.domain.entity.OrderProduct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderCreateValidator {

    public void validate(Order order) {

        Validator validator = new Validator();

        validator.add(Validation.notEmpty(order.getProducts(), "É obrigatório informar os produtos"));

        if (order.getProducts() != null) {
            for (OrderProduct orderProduct : order.getProducts()) {
                validator.add(Validation.notNull(orderProduct.getQuantity(), "É obrigatório informar a quantidade"));
                validator.add(Validation.greaterThan(orderProduct.getQuantity(), 0, "A quantidade deve ser maior que zero"));
                validator.add(Validation.notNull(orderProduct.getProduct(), "É obrigatório informar o produto"));
            }
        }

        validator.assertEmptyMessages();
    }

}

package com.fiap.challenge.tastefood.core.application.validator;

import com.fiap.challenge.tastefood.core.application.util.validation.Validation;
import com.fiap.challenge.tastefood.core.application.util.validation.Validator;
import com.fiap.challenge.tastefood.core.application.vo.OrderInput;
import com.fiap.challenge.tastefood.core.application.vo.OrderProductInput;
import com.fiap.challenge.tastefood.core.domain.repository.CustomerRepository;
import com.fiap.challenge.tastefood.core.domain.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderCreateValidator {

    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public void validate(OrderInput orderInput) {

        Validator validator = new Validator();

        validator.add(Validation.assertFalse(customerNotFound(orderInput.getCustomerId()), "Cliente com ID %s não encontrado", orderInput.getCustomerId()));
        validator.add(Validation.notEmpty(orderInput.getProducts(), "É obrigatório informar os produtos"));

        if (orderInput.getProducts() != null) {
            for (OrderProductInput orderProductInput : orderInput.getProducts()) {
                validator.add(Validation.notNull(orderProductInput.getQuantity(), "É obrigatório informar a quantidade"));
                validator.add(Validation.greaterThan(orderProductInput.getQuantity(), 0, "A quantidade deve ser maior que zero"));
                validator.add(Validation.notNull(orderProductInput.getProductId(), "É obrigatório informar o produto"));
                validator.add(Validation.assertFalse(productNotFound(orderProductInput.getProductId()), "Produto com ID %s não encontrado", orderProductInput.getProductId()));
            }
        }

        validator.assertEmptyMessages();
    }

    private boolean customerNotFound(Long customerId) {
        return customerId != null && customerRepository.findById(customerId).isEmpty();
    }

    private boolean productNotFound(Long productId) {
        return productId != null && productRepository.findById(productId).isEmpty();
    }

}

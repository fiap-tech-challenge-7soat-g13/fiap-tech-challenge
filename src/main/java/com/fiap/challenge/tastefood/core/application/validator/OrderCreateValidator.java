package com.fiap.challenge.tastefood.core.application.validator;

import com.fiap.challenge.tastefood.core.application.dto.OrderProductRequest;
import com.fiap.challenge.tastefood.core.application.dto.OrderRequest;
import com.fiap.challenge.tastefood.core.domain.repository.CustomerRepository;
import com.fiap.challenge.tastefood.core.domain.repository.ProductRepository;
import com.fiap.challenge.tastefood.core.application.util.validation.Validation;
import com.fiap.challenge.tastefood.core.application.util.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderCreateValidator {

    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public void validate(OrderRequest order) {

        Validator validator = new Validator();

        validator.add(Validation.assertFalse(customerNotFound(order.getCustomerId()), "Cliente com ID %s não encontrado", order.getCustomerId()));
        validator.add(Validation.notEmpty(order.getProducts(), "É obrigatório informar os produtos"));

        if (order.getProducts() != null) {
            for (OrderProductRequest orderProduct : order.getProducts()) {
                validator.add(Validation.notNull(orderProduct.getQuantity(), "É obrigatório informar a quantidade"));
                validator.add(Validation.greaterThan(orderProduct.getQuantity(), 0, "A quantidade deve ser maior que zero"));
                validator.add(Validation.notNull(orderProduct.getProductId(), "É obrigatório informar o produto"));
                validator.add(Validation.assertFalse(productNotFound(orderProduct.getProductId()), "Produto com ID %s não encontrado", orderProduct.getProductId()));
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

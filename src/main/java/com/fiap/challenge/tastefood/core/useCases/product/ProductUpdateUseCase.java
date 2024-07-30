package com.fiap.challenge.tastefood.core.useCases.product;

import com.fiap.challenge.tastefood.core.common.validator.ProductUpdateValidator;
import com.fiap.challenge.tastefood.core.domain.Product;
import com.fiap.challenge.tastefood.core.gateways.ProductGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductUpdateUseCase {

    private final ProductGateway productGateway;
    private final ProductUpdateValidator validator;

    public void execute(Long id, Product product) {
        validator.validate(id, product, productGateway);

        productGateway.update(id, product);
    }

}

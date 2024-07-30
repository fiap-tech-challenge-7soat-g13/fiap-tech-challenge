package com.fiap.challenge.tastefood.core.useCases.product;

import com.fiap.challenge.tastefood.core.common.validator.ProductCreateValidator;
import com.fiap.challenge.tastefood.core.domain.Product;
import com.fiap.challenge.tastefood.core.gateways.ProductGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductCreateUseCase {

    private final ProductGateway productGateway;
    private final ProductCreateValidator validator;

    public Long execute(Product product) {
        validator.validate(product);
        product.setActive(true);

        Product saved = productGateway.save(product);

        return saved.getId();
    }

}

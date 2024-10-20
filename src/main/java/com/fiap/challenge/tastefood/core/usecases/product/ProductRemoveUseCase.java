package com.fiap.challenge.tastefood.core.usecases.product;

import com.fiap.challenge.tastefood.core.common.exception.EntityNotFoundException;
import com.fiap.challenge.tastefood.core.domain.Product;
import com.fiap.challenge.tastefood.core.gateways.ProductGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductRemoveUseCase {

    private final ProductGateway productGateway;

    public void execute(Long id) {
        Product product = productGateway.findById(id).orElseThrow(EntityNotFoundException::new);
        product.setActive(false);
        productGateway.save(product);
    }

}

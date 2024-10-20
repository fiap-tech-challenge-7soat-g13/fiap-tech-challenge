package com.fiap.challenge.tastefood.core.usecases.product;

import com.fiap.challenge.tastefood.core.domain.Product;
import com.fiap.challenge.tastefood.core.domain.enums.ProductCategory;
import com.fiap.challenge.tastefood.core.gateways.ProductGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductListUseCase {

    private final ProductGateway productGateway;

    public List<Product> execute(ProductCategory category) {
        return category == null ? productGateway.findByActiveTrue() : productGateway.findByCategoryAndActiveTrue(category);
    }

}

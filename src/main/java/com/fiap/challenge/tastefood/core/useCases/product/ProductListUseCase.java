package com.fiap.challenge.tastefood.core.useCases.product;

import com.fiap.challenge.tastefood.app.adapter.output.persistence.mapper.ProductMapper;
import com.fiap.challenge.tastefood.core.domain.enums.ProductCategory;
import com.fiap.challenge.tastefood.core.domain.Product;
import com.fiap.challenge.tastefood.core.gateways.ProductGateway;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductListUseCase {

    private final ProductMapper mapper;
    private final ProductGateway productGateway;

    @Transactional
    public List<Product> execute(ProductCategory category) {
        return mapper.toProduct(category == null ? productGateway.findByActiveTrue()
                : productGateway.findByCategoryAndActiveTrue(category));
    }

}

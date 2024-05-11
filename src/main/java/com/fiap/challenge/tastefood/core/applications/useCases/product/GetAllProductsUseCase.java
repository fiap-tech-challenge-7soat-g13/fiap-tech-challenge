package com.fiap.challenge.tastefood.core.applications.useCases.product;

import com.fiap.challenge.tastefood.core.applications.dtos.Product;
import com.fiap.challenge.tastefood.core.domain.entities.ProductEntity;
import com.fiap.challenge.tastefood.core.domain.mapper.ProductMapper;
import com.fiap.challenge.tastefood.core.domain.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllProductsUseCase {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<Product> execute() {
        List<ProductEntity> products = (List<ProductEntity>) productRepository.findAll();
        if (!products.isEmpty())
            return products.stream().map(this.productMapper::fromProductEntity).toList();

        return List.of();
    }

}

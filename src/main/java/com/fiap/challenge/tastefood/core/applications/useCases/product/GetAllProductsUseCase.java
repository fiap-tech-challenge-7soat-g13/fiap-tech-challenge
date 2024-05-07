package com.fiap.challenge.tastefood.core.applications.useCases.product;

import com.fiap.challenge.tastefood.core.applications.dtos.Product;
import com.fiap.challenge.tastefood.core.domain.entities.ProductEntity;
import com.fiap.challenge.tastefood.core.domain.mapper.ProductMapper;
import com.fiap.challenge.tastefood.core.domain.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllProductsUseCase {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Autowired
    public GetAllProductsUseCase(ProductRepository productRepository,
                                 ProductMapper productMapper) {
        this.productRepository = productRepository;
	    this.productMapper = productMapper;
    }

    public List<Product> execute() {
        List<ProductEntity> products = (List<ProductEntity>) productRepository.findAll();
        if (!products.isEmpty())
            return products.stream().map(this.productMapper::fromProductEntity).toList();

        return List.of();
    }

}

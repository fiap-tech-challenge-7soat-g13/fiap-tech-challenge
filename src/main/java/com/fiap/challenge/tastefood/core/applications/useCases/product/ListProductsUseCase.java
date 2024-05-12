package com.fiap.challenge.tastefood.core.applications.useCases.product;

import com.fiap.challenge.tastefood.core.applications.dtos.CategoryEnum;
import com.fiap.challenge.tastefood.core.applications.dtos.Product;
import com.fiap.challenge.tastefood.core.domain.entities.ProductEntity;
import com.fiap.challenge.tastefood.core.domain.mapper.ProductMapper;
import com.fiap.challenge.tastefood.core.domain.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ListProductsUseCase {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Transactional
    public List<Product> execute(CategoryEnum category) {
        List<ProductEntity> products = category == null ? productRepository.findAll() : productRepository.findByCategory(com.fiap.challenge.tastefood.core.domain.entities.CategoryEnum.valueOf(category.name()));
        return products.stream().map(this.productMapper::fromProductEntity).toList();
    }

}

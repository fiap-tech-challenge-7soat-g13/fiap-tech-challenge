package com.fiap.challenge.tastefood.core.applications.useCases.product;

import com.fiap.challenge.tastefood.core.applications.dtos.CategoryEnum;
import com.fiap.challenge.tastefood.core.applications.dtos.Product;
import com.fiap.challenge.tastefood.core.domain.entities.ProductEntity;
import com.fiap.challenge.tastefood.core.domain.mapper.ProductMapper;
import com.fiap.challenge.tastefood.core.domain.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GetProductsByCategoryUseCase {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<Product> execute(String category) {
        CategoryEnum categoryEnum1 = CategoryEnum.valueOf(category.toUpperCase());
        Optional<List<ProductEntity>> products = productRepository.findByCategory(categoryEnum1);

        if (products.isPresent() && !products.get().isEmpty())
            return products.get().stream().map(this.productMapper::fromProductEntity).toList();

        return List.of();
    }

}

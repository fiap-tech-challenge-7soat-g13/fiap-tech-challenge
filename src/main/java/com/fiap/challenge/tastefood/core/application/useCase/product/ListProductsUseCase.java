package com.fiap.challenge.tastefood.core.application.useCase.product;

import com.fiap.challenge.tastefood.core.domain.entity.Product;
import com.fiap.challenge.tastefood.core.domain.entity.ProductCategoryEnum;
import com.fiap.challenge.tastefood.core.domain.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ListProductsUseCase {

    private final ProductRepository productRepository;

    @Transactional
    public List<Product> execute(ProductCategoryEnum category) {
        return category == null ? productRepository.findAll() : productRepository.findByCategory(category);
    }

}

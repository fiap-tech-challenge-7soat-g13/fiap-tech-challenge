package com.fiap.challenge.tastefood.core.application.useCase.product;

import com.fiap.challenge.tastefood.core.domain.entity.Product;
import com.fiap.challenge.tastefood.core.domain.valueObject.ProductCategory;
import com.fiap.challenge.tastefood.core.domain.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductListUseCase {

    private final ProductRepository repository;

    @Transactional
    public List<Product> execute(ProductCategory category) {
        return category == null ? repository.findByActiveTrue() : repository.findByCategoryAndActiveTrue(category);
    }

}

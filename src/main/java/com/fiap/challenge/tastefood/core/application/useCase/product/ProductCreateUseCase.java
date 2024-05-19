package com.fiap.challenge.tastefood.core.application.useCase.product;

import com.fiap.challenge.tastefood.core.domain.entity.Product;
import com.fiap.challenge.tastefood.core.domain.repository.ProductRepository;
import com.fiap.challenge.tastefood.core.domain.validation.CreateProductValidator;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductCreateUseCase {

    private final ProductRepository productRepository;
    private final CreateProductValidator productValidator;

    @Transactional
    public Long execute(Product product) {

        productValidator.validate(product);

        product.setRemoved(false);

        Product saved = productRepository.save(product);

        return saved.getId();
    }

}

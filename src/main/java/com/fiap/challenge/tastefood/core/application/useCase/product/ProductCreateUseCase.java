package com.fiap.challenge.tastefood.core.application.useCase.product;

import com.fiap.challenge.tastefood.core.domain.entity.Product;
import com.fiap.challenge.tastefood.core.domain.repository.ProductRepository;
import com.fiap.challenge.tastefood.core.domain.validation.ProductCreateValidator;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductCreateUseCase {

    private final ProductRepository repository;
    private final ProductCreateValidator validator;

    @Transactional
    public Long execute(Product product) {

        validator.validate(product);

        product.setRemoved(false);

        Product saved = repository.save(product);

        return saved.getId();
    }

}

package com.fiap.challenge.tastefood.core.application.useCase.product;

import com.fiap.challenge.tastefood.core.domain.entity.Product;
import com.fiap.challenge.tastefood.core.domain.exception.EntityNotFoundException;
import com.fiap.challenge.tastefood.core.domain.repository.ProductRepository;
import com.fiap.challenge.tastefood.core.domain.validation.ProductUpdateValidator;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductUpdateUseCase {

    private final ProductRepository repository;

    private final ProductUpdateValidator validator;

    @Transactional
    public void execute(Long id, Product product) {

        validator.validate(id, product);

        Product entity = repository.findById(id).orElseThrow(EntityNotFoundException::new);

        entity.setName(product.getName());
        entity.setDescription(product.getDescription());
        entity.setCategory(product.getCategory());
        entity.setPrice(product.getPrice());
        entity.setImages(product.getImages());

        repository.save(entity);
    }

}

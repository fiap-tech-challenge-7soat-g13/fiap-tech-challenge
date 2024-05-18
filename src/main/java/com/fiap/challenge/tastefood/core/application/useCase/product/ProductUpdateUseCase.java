package com.fiap.challenge.tastefood.core.application.useCase.product;

import com.fiap.challenge.tastefood.core.domain.entity.Product;
import com.fiap.challenge.tastefood.core.domain.exception.EntityNotFoundException;
import com.fiap.challenge.tastefood.core.domain.exception.InvalidDataException;
import com.fiap.challenge.tastefood.core.domain.repository.ProductRepository;
import com.fiap.challenge.tastefood.core.domain.validation.UpdateProductValidator;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductUpdateUseCase {

    private final ProductRepository productRepository;

    private final UpdateProductValidator validator;

    @Transactional
    public void execute(Long id, Product product) throws InvalidDataException {

        validator.validate(id, product);

        Product entity = productRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        entity.setName(product.getName());
        entity.setDescription(product.getDescription());
        entity.setCategory(product.getCategory());
        entity.setPrice(product.getPrice());

        productRepository.save(entity);
    }

}

package com.fiap.challenge.tastefood.core.applications.useCases.product;

import com.fiap.challenge.tastefood.core.applications.dtos.Product;
import com.fiap.challenge.tastefood.core.domain.entities.CategoryEnum;
import com.fiap.challenge.tastefood.core.domain.entities.ProductEntity;
import com.fiap.challenge.tastefood.core.domain.exception.EntityNotFoundException;
import com.fiap.challenge.tastefood.core.domain.exception.InvalidDataException;
import com.fiap.challenge.tastefood.core.domain.repositories.ProductRepository;
import com.fiap.challenge.tastefood.core.domain.validation.UpdateProductValidator;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateProductUseCase {

    private final ProductRepository productRepository;

    private final UpdateProductValidator validator;

    @Transactional
    public void execute(Long id, Product product) throws InvalidDataException {

        validator.validate(id, product);

        ProductEntity entity = productRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        entity.setDescription(product.getDescription());
        entity.setBrand(product.getBrand());
        entity.setCategory(CategoryEnum.valueOf(product.getCategory().name()));
        entity.setValue(product.getValue());

        productRepository.save(entity);
    }

}

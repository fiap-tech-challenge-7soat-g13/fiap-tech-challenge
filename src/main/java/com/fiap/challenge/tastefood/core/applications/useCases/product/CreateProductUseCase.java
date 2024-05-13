package com.fiap.challenge.tastefood.core.applications.useCases.product;

import com.fiap.challenge.tastefood.core.applications.dtos.Product;
import com.fiap.challenge.tastefood.core.domain.entities.ProductEntity;
import com.fiap.challenge.tastefood.core.domain.mapper.ProductMapper;
import com.fiap.challenge.tastefood.core.domain.repositories.ProductRepository;
import com.fiap.challenge.tastefood.core.domain.validation.CreateProductValidator;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateProductUseCase {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CreateProductValidator productValidator;

    @Transactional
    public Long execute(Product product) {

        productValidator.validate(product);

        ProductEntity entity = this.productMapper.toProductEntity(product);

        entity = productRepository.save(entity);

        return entity.getId();
    }

}

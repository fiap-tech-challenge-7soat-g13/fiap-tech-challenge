package com.fiap.challenge.tastefood.core.application.useCase.product;

import com.fiap.challenge.tastefood.core.application.dto.ProductRequest;
import com.fiap.challenge.tastefood.core.domain.entity.Product;
import com.fiap.challenge.tastefood.core.application.exception.EntityNotFoundException;
import com.fiap.challenge.tastefood.core.application.mapper.ProductRequestMapper;
import com.fiap.challenge.tastefood.core.domain.repository.ProductRepository;
import com.fiap.challenge.tastefood.core.application.validator.ProductUpdateValidator;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductUpdateUseCase {

    private final ProductRequestMapper mapper;
    private final ProductRepository repository;
    private final ProductUpdateValidator validator;

    @Transactional
    public void execute(Long id, ProductRequest product) {

        validator.validate(id, product);

        Product entity = repository.findById(id).orElseThrow(EntityNotFoundException::new);

        mapper.update(product, entity);

        repository.save(entity);
    }

}

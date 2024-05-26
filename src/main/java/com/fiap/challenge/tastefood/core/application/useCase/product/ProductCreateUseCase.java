package com.fiap.challenge.tastefood.core.application.useCase.product;

import com.fiap.challenge.tastefood.core.application.dto.ProductRequest;
import com.fiap.challenge.tastefood.core.domain.entity.Product;
import com.fiap.challenge.tastefood.core.application.mapper.ProductRequestMapper;
import com.fiap.challenge.tastefood.core.domain.repository.ProductRepository;
import com.fiap.challenge.tastefood.core.application.validator.ProductCreateValidator;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductCreateUseCase {

    private final ProductRequestMapper mapper;
    private final ProductRepository repository;
    private final ProductCreateValidator validator;

    @Transactional
    public Long execute(ProductRequest product) {

        validator.validate(product);

        Product entity = mapper.map(product);

        entity.setActive(true);

        Product saved = repository.save(entity);

        return saved.getId();
    }

}

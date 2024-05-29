package com.fiap.challenge.tastefood.core.application.useCase.product;

import com.fiap.challenge.tastefood.core.application.mapper.ProductInputMapper;
import com.fiap.challenge.tastefood.core.application.validator.ProductCreateValidator;
import com.fiap.challenge.tastefood.core.application.vo.ProductInput;
import com.fiap.challenge.tastefood.core.domain.entity.Product;
import com.fiap.challenge.tastefood.core.domain.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductCreateUseCase {

    private final ProductInputMapper mapper;
    private final ProductRepository repository;
    private final ProductCreateValidator validator;

    @Transactional
    public Long execute(ProductInput productInput) {

        validator.validate(productInput);

        Product entity = mapper.toProduct(productInput);

        entity.setActive(true);

        Product saved = repository.save(entity);

        return saved.getId();
    }

}

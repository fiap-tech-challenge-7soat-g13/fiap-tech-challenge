package com.fiap.challenge.tastefood.core.application.useCase.product;

import com.fiap.challenge.tastefood.core.application.exception.EntityNotFoundException;
import com.fiap.challenge.tastefood.core.application.mapper.ProductInputMapper;
import com.fiap.challenge.tastefood.core.application.validator.ProductUpdateValidator;
import com.fiap.challenge.tastefood.core.application.vo.ProductInput;
import com.fiap.challenge.tastefood.core.domain.entity.Product;
import com.fiap.challenge.tastefood.core.domain.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductUpdateUseCase {

    private final ProductInputMapper mapper;
    private final ProductRepository repository;
    private final ProductUpdateValidator validator;

    @Transactional
    public void execute(Long id, ProductInput productInput) {

        validator.validate(id, productInput);

        Product entity = repository.findById(id).orElseThrow(EntityNotFoundException::new);

        mapper.update(productInput, entity);

        repository.save(entity);
    }

}

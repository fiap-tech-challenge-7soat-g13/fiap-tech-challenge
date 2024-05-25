package com.fiap.challenge.tastefood.core.application.useCase.product;

import com.fiap.challenge.tastefood.core.domain.entity.Product;
import com.fiap.challenge.tastefood.core.domain.exception.EntityNotFoundException;
import com.fiap.challenge.tastefood.core.domain.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductRemoveUseCase {

    private final ProductRepository repository;

    @Transactional
    public void execute(Long id) {
        Product entity = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        entity.setActive(false);
        repository.save(entity);
    }

}

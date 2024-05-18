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

    private final ProductRepository productRepository;

    @Transactional
    public void execute(Long id) {
        Product entity = productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        productRepository.delete(entity);
    }

}

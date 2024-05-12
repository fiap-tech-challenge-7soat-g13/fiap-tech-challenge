package com.fiap.challenge.tastefood.core.applications.useCases.product;

import com.fiap.challenge.tastefood.core.domain.entities.ProductEntity;
import com.fiap.challenge.tastefood.core.domain.exception.EntityNotFoundException;
import com.fiap.challenge.tastefood.core.domain.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RemoveProductUseCase {

    private final ProductRepository productRepository;

    @Transactional
    public void execute(Long id) {
        ProductEntity entity = productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        productRepository.delete(entity);
    }

}

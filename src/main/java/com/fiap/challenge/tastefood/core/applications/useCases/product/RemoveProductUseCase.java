package com.fiap.challenge.tastefood.core.applications.useCases.product;

import com.fiap.challenge.tastefood.core.domain.entities.ProductEntity;
import com.fiap.challenge.tastefood.core.domain.exception.OrderException;
import com.fiap.challenge.tastefood.core.domain.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class RemoveProductUseCase {

    private final ProductRepository productRepository;

    public void execute(Long id) throws OrderException {
        Optional<ProductEntity> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.delete(product.get());
            log.info("Produto removido com sucesso!");
        }

        throw new OrderException(String.format("Produto com id: %s não existe!", id));
    }

}

package com.fiap.challenge.tastefood.core.useCases.product;

import com.fiap.challenge.tastefood.app.adapter.output.persistence.entity.ProductEntity;
import com.fiap.challenge.tastefood.core.common.exception.EntityNotFoundException;
import com.fiap.challenge.tastefood.core.gateways.ProductGateway;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductRemoveUseCase {

    private final ProductGateway productGateway;

    @Transactional
    public void execute(Long id) {
        ProductEntity entity = productGateway.findById(id).orElseThrow(EntityNotFoundException::new);
        entity.setActive(false);
        productGateway.save(entity);
    }

}

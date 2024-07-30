package com.fiap.challenge.tastefood.core.useCases.product;

import com.fiap.challenge.tastefood.app.adapter.output.persistence.entity.ProductEntity;
import com.fiap.challenge.tastefood.app.adapter.output.persistence.mapper.ProductMapper;
import com.fiap.challenge.tastefood.core.common.exception.EntityNotFoundException;
import com.fiap.challenge.tastefood.core.common.validator.ProductUpdateValidator;
import com.fiap.challenge.tastefood.core.domain.Product;
import com.fiap.challenge.tastefood.core.gateways.ProductGateway;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductUpdateUseCase {

    private final ProductMapper mapper;
    private final ProductGateway productGateway;
    private final ProductUpdateValidator validator;

    @Transactional
    public void execute(Long id, Product product) {

        validator.validate(id, product);

        ProductEntity entity = productGateway.findById(id).orElseThrow(EntityNotFoundException::new);

        mapper.update(product, entity);

        productGateway.save(entity);
    }

}

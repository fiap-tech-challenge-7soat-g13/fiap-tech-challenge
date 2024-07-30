package com.fiap.challenge.tastefood.core.useCases.product;

import com.fiap.challenge.tastefood.app.adapter.output.persistence.entity.ProductEntity;
import com.fiap.challenge.tastefood.app.adapter.output.persistence.mapper.ProductMapper;
import com.fiap.challenge.tastefood.core.common.validator.ProductCreateValidator;
import com.fiap.challenge.tastefood.core.domain.Product;
import com.fiap.challenge.tastefood.core.gateways.ProductGateway;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductCreateUseCase {

    private final ProductMapper mapper;
    private final ProductGateway productGateway;
    private final ProductCreateValidator validator;

    @Transactional
    public Long execute(Product product) {

        validator.validate(product);

        ProductEntity entity = mapper.toProductEntity(product);

        entity.setActive(true);

        ProductEntity saved = productGateway.save(entity);

        return saved.getId();
    }

}

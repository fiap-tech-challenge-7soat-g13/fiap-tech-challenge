package com.fiap.challenge.tastefood.core.domain.gateways;

import com.fiap.challenge.tastefood.adapter.driven.infra.ProductGateway;
import com.fiap.challenge.tastefood.core.domain.entities.ProductEntity;
import com.fiap.challenge.tastefood.core.domain.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ProductGatewayImpl implements ProductGateway {

    private final ProductRepository productRepository;

    public List<ProductEntity> findAll() {
        return (List<ProductEntity>) productRepository.findAll();
    }

    public Optional<ProductEntity> findById(Long id) {
        return productRepository.findById(id);
    }

}

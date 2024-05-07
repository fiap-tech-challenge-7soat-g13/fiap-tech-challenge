package com.fiap.challenge.tastefood.adapter.driven.infra;

import com.fiap.challenge.tastefood.core.domain.entities.ProductEntity;

import java.util.Optional;

public interface ProductGateway {

	ProductEntity findAll();

	Optional<ProductEntity> findById(Long id);

}

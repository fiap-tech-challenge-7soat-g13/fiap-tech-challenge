package com.fiap.challenge.tastefood.core.gateways;

import com.fiap.challenge.tastefood.app.adapter.output.persistence.entity.ProductEntity;
import com.fiap.challenge.tastefood.core.domain.enums.ProductCategory;

import java.util.List;
import java.util.Optional;

public interface ProductGateway {

	ProductEntity save(ProductEntity productEntity);

	Optional<ProductEntity> findById(Long id);

	List<ProductEntity> findByActiveTrue();

	List<ProductEntity> findByCategoryAndActiveTrue(ProductCategory category);

	Optional<ProductEntity> findByNameAndActiveTrue(String name);

}

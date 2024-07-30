package com.fiap.challenge.tastefood.app.adapter.output.persistence.gateway;

import com.fiap.challenge.tastefood.app.adapter.output.persistence.entity.ProductEntity;
import com.fiap.challenge.tastefood.app.adapter.output.persistence.repository.ProductRepository;
import com.fiap.challenge.tastefood.core.domain.enums.ProductCategory;
import com.fiap.challenge.tastefood.core.gateways.ProductGateway;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class ProductGatewayImpl implements ProductGateway {

	private final ProductRepository repository;

	public ProductEntity save(ProductEntity productEntity) {
		return repository.save(productEntity);
	}

	public Optional<ProductEntity> findById(Long id) {
		return repository.findById(id);
	}

	public List<ProductEntity> findByActiveTrue() {
		return repository.findByActiveTrue();
	}

	public List<ProductEntity> findByCategoryAndActiveTrue(ProductCategory category) {
		return repository.findByCategoryAndActiveTrue(category);
	}

	public Optional<ProductEntity> findByNameAndActiveTrue(String name) {
		return repository.findByNameAndActiveTrue(name);
	}

}

package com.fiap.challenge.tastefood.app.adapter.output.persistence.gateway;

import com.fiap.challenge.tastefood.app.adapter.output.persistence.entity.ProductEntity;
import com.fiap.challenge.tastefood.app.adapter.output.persistence.mapper.ProductMapper;
import com.fiap.challenge.tastefood.app.adapter.output.persistence.repository.ProductRepository;
import com.fiap.challenge.tastefood.core.common.exception.EntityNotFoundException;
import com.fiap.challenge.tastefood.core.domain.Product;
import com.fiap.challenge.tastefood.core.domain.enums.ProductCategory;
import com.fiap.challenge.tastefood.core.gateways.ProductGateway;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ProductGatewayImpl implements ProductGateway {

	private final ProductMapper productMapper;
	private final ProductRepository repository;

	@Transactional
	public Product save(Product product) {
		ProductEntity productEntity = productMapper.toProductEntity(product);
		ProductEntity productSave = repository.save(productEntity);
		return productMapper.toProduct(productSave);
	}

	public Optional<Product> findById(Long id) {
		Optional<ProductEntity> productEntity = repository.findById(id);
		return productEntity.map(productMapper::toProduct);
	}

	@Transactional
	public List<Product> findByActiveTrue() {
		List<ProductEntity> productList = repository.findByActiveTrue();
		return productMapper.toProduct(productList);
	}

	@Transactional
	public List<Product> findByCategoryAndActiveTrue(ProductCategory category) {
		List<ProductEntity> productList = repository.findByCategoryAndActiveTrue(category);
		return productMapper.toProduct(productList);
	}

	public Optional<Product> findByNameAndActiveTrue(String name) {
		Optional<ProductEntity> productEntity = repository.findByNameAndActiveTrue(name);
		return productEntity.isPresent() ? productEntity.map(productMapper::toProduct) : Optional.empty();
	}

	public Product update(Product product) {
		Product productSave = findById(product.getId()).orElseThrow(EntityNotFoundException::new);
		product.setActive(productSave.getActive());
		productMapper.update(product, productSave);
		return save(productSave);
	}

}

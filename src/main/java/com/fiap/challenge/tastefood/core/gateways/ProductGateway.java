package com.fiap.challenge.tastefood.core.gateways;

import com.fiap.challenge.tastefood.core.domain.Product;
import com.fiap.challenge.tastefood.core.domain.enums.ProductCategory;

import java.util.List;
import java.util.Optional;

public interface ProductGateway {

	Product save(Product product);

	Optional<Product> findById(Long id);

	List<Product> findByActiveTrue();

	List<Product> findByCategoryAndActiveTrue(ProductCategory category);

	Optional<Product> findByNameAndActiveTrue(String name);

	Product update(Long id, Product product);

}

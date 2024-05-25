package com.fiap.challenge.tastefood.core.domain.repository;

import com.fiap.challenge.tastefood.core.domain.entity.Product;
import com.fiap.challenge.tastefood.core.domain.valueObject.ProductCategory;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Product save(Product product);

    Optional<Product> findById(Long id);

    List<Product> findByActiveTrue();

    List<Product> findByCategoryAndActiveTrue(ProductCategory category);

    Optional<Product> findByNameAndActiveTrue(String name);

}

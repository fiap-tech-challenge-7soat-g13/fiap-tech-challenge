package com.fiap.challenge.tastefood.adapter.driver.infra;

import com.fiap.challenge.tastefood.core.domain.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductGateway {

	List<Product> findAll();

	Optional<Product> findById(Long id);

}

package com.fiap.challenge.tastefood.adapter.driven.infra.repository;

import com.fiap.challenge.tastefood.core.domain.entity.Product;
import com.fiap.challenge.tastefood.core.domain.repository.ProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductJpaRepository extends ProductRepository, JpaRepository<Product, Long> {

}

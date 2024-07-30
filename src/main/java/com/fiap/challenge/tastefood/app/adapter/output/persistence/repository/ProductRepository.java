package com.fiap.challenge.tastefood.app.adapter.output.persistence.repository;

import com.fiap.challenge.tastefood.app.adapter.output.persistence.entity.ProductEntity;
import com.fiap.challenge.tastefood.core.domain.enums.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    ProductEntity save(ProductEntity productEntity);

    Optional<ProductEntity> findById(Long id);

    List<ProductEntity> findByActiveTrue();

    List<ProductEntity> findByCategoryAndActiveTrue(ProductCategory category);

    Optional<ProductEntity> findByNameAndActiveTrue(String name);

}

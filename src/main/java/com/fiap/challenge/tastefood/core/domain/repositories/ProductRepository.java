package com.fiap.challenge.tastefood.core.domain.repositories;

import com.fiap.challenge.tastefood.core.domain.entities.CategoryEnum;
import com.fiap.challenge.tastefood.core.domain.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    Optional<ProductEntity> findByDescription(String description);

    List<ProductEntity> findByCategory(CategoryEnum categoryEnum);

}

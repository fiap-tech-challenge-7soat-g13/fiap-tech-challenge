package com.fiap.challenge.tastefood.core.domain.repositories;

import com.fiap.challenge.tastefood.core.applications.dtos.CategoryEnum;
import com.fiap.challenge.tastefood.core.domain.entities.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {

    Optional<ProductEntity> findByDescription(String description);

    Optional<List<ProductEntity>> findByCategory(CategoryEnum categoryEnum);

}
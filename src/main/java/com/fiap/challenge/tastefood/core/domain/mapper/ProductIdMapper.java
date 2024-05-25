package com.fiap.challenge.tastefood.core.domain.mapper;

import com.fiap.challenge.tastefood.core.domain.entity.Product;
import com.fiap.challenge.tastefood.core.domain.repository.ProductRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class ProductIdMapper {

    @Autowired
    private ProductRepository productRepository;

    public Product map(Long id) {
        if (id == null) {
            return null;
        }
        return productRepository.findById(id).orElse(null);
    }

}

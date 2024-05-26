package com.fiap.challenge.tastefood.core.application.mapper;

import com.fiap.challenge.tastefood.core.application.dto.ProductRequest;
import com.fiap.challenge.tastefood.core.domain.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductRequestMapper {

    Product map(ProductRequest product);

    void update(ProductRequest product, @MappingTarget Product entity);

}

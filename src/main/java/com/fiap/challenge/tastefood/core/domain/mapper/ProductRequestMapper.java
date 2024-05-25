package com.fiap.challenge.tastefood.core.domain.mapper;

import com.fiap.challenge.tastefood.core.application.dto.ProductRequest;
import com.fiap.challenge.tastefood.core.domain.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductRequestMapper {

    Product map(ProductRequest productFormDto);

}

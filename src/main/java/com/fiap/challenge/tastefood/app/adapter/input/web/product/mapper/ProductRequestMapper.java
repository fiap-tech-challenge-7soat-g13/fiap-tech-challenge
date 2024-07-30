package com.fiap.challenge.tastefood.app.adapter.input.web.product.mapper;

import com.fiap.challenge.tastefood.app.adapter.input.web.order.dto.ProductRequest;
import com.fiap.challenge.tastefood.core.domain.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductRequestMapper {

    Product toProduct(ProductRequest productRequest);

    void update(ProductRequest productRequest, @MappingTarget Product product);

}

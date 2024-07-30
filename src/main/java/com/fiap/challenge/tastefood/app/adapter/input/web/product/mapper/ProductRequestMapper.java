package com.fiap.challenge.tastefood.app.adapter.input.web.product.mapper;

import com.fiap.challenge.tastefood.app.adapter.input.web.order.dto.ProductRequest;
import com.fiap.challenge.tastefood.core.domain.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductRequestMapper {

    Product toProduct(ProductRequest productRequest);

}

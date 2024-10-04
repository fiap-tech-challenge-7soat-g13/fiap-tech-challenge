package com.fiap.challenge.tastefood.app.adapter.input.web.product.mapper;

import com.fiap.challenge.tastefood.app.adapter.input.web.product.dto.ProductResponse;
import com.fiap.challenge.tastefood.core.domain.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductResponseMapper {

    ProductResponse toProductResponse(Product products);

    List<ProductResponse> toProductResponse(List<Product> products);

}

package com.fiap.challenge.tastefood.core.application.mapper;

import com.fiap.challenge.tastefood.core.application.dto.ProductResponse;
import com.fiap.challenge.tastefood.core.domain.entity.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductResponseMapper {

    List<ProductResponse> map(List<Product> products);

}

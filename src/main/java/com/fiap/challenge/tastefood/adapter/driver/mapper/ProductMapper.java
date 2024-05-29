package com.fiap.challenge.tastefood.adapter.driver.mapper;

import com.fiap.challenge.tastefood.adapter.driver.dto.ProductRequest;
import com.fiap.challenge.tastefood.adapter.driver.dto.ProductResponse;
import com.fiap.challenge.tastefood.core.application.vo.ProductInput;
import com.fiap.challenge.tastefood.core.application.vo.ProductOutput;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductInput toProductInput(ProductRequest product);

    List<ProductResponse> toProductResponse(List<ProductOutput> productsOutput);

}

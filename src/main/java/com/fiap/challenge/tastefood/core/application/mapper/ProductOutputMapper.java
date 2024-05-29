package com.fiap.challenge.tastefood.core.application.mapper;

import com.fiap.challenge.tastefood.core.application.vo.ProductOutput;
import com.fiap.challenge.tastefood.core.domain.entity.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductOutputMapper {

    List<ProductOutput> toProductOutput(List<Product> products);

}

package com.fiap.challenge.tastefood.core.application.mapper;

import com.fiap.challenge.tastefood.adapter.driver.dto.ProductResponse;
import com.fiap.challenge.tastefood.core.application.vo.ProductOutput;
import com.fiap.challenge.tastefood.core.domain.entity.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductOutputMapper {

    List<ProductOutput> map(List<Product> products);

}

package com.fiap.challenge.tastefood.core.domain.mapper;

import com.fiap.challenge.tastefood.adapter.driver.dto.OrderProductRequest;
import com.fiap.challenge.tastefood.core.domain.entity.OrderProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ProductIdMapper.class)
public interface OrderProductRequestMapper {

    @Mapping(source = "productId", target = "product")
    OrderProduct map(OrderProductRequest orderProduct);

}

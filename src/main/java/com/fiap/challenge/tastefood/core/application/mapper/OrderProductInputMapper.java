package com.fiap.challenge.tastefood.core.application.mapper;

import com.fiap.challenge.tastefood.core.application.vo.OrderProductInput;
import com.fiap.challenge.tastefood.core.domain.entity.OrderProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ProductIdMapper.class)
public interface OrderProductInputMapper {

    @Mapping(source = "productId", target = "product")
    OrderProduct toOrderProduct(OrderProductInput orderProduct);

}

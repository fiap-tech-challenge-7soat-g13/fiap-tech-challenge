package com.fiap.challenge.tastefood.app.adapter.input.web.order.mapper;

import com.fiap.challenge.tastefood.app.adapter.input.web.product.dto.OrderProductRequest;
import com.fiap.challenge.tastefood.core.domain.OrderProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderProductRequestMapper {

    @Mapping(source = "productId", target = "product.id")
    OrderProduct toOrderProduct(OrderProductRequest orderProduct);

}

package com.fiap.challenge.tastefood.app.adapter.output.persistence.mapper;

import com.fiap.challenge.tastefood.app.adapter.output.persistence.entity.OrderProductEntity;
import com.fiap.challenge.tastefood.core.domain.OrderProduct;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderProductMapper {

    OrderProductEntity toOrderProductEntity(OrderProduct orderProduct);

}

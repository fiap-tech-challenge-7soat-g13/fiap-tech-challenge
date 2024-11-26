package com.fiap.challenge.tastefood.app.adapter.output.externalapis.payment;

import com.fiap.challenge.tastefood.core.domain.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreatePaymentOrderRequestMapper {

    CreatePaymentOrderRequest toCreatePaymentOrderRequest(Order order);

}

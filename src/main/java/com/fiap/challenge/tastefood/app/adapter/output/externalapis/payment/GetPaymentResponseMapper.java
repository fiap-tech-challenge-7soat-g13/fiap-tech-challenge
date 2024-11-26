package com.fiap.challenge.tastefood.app.adapter.output.externalapis.payment;

import com.fiap.challenge.tastefood.core.domain.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GetPaymentResponseMapper {

    Payment toPayment(GetPaymentResponse getPaymentResponse);

}

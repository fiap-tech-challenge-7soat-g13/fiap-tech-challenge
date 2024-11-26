package com.fiap.challenge.tastefood.app.adapter.input.queue.payment.mapper;

import com.fiap.challenge.tastefood.app.adapter.input.queue.payment.dto.PaymentStatusChangedEvent;
import com.fiap.challenge.tastefood.core.domain.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentStatusChangedEventMapper {

    Payment toPayment(PaymentStatusChangedEvent event);

}

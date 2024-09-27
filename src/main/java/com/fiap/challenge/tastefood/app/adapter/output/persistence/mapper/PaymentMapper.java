package com.fiap.challenge.tastefood.app.adapter.output.persistence.mapper;

import com.fiap.challenge.tastefood.app.adapter.output.persistence.entity.PaymentEntity;
import com.fiap.challenge.tastefood.core.domain.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {OrderProductMapper.class})
public interface PaymentMapper {

    Payment toPayment(PaymentEntity paymentEntity);

}

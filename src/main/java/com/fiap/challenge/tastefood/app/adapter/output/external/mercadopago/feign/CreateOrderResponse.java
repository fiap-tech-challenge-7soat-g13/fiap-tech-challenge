package com.fiap.challenge.tastefood.app.adapter.output.external.mercadopago.feign;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateOrderResponse {

    private String qrData;
    private String inStoreOrderId;

}

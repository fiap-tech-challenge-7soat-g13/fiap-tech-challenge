package com.fiap.challenge.tastefood.app.adapter.output.external.mercadopago.feign;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateOrderItemRequest {

    private String title;
    private BigDecimal unitPrice;
    private Long quantity;
    private String unitMeasure;
    private BigDecimal totalAmount;

}

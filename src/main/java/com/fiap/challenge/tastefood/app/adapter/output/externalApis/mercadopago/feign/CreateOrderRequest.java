package com.fiap.challenge.tastefood.app.adapter.output.externalApis.mercadopago.feign;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateOrderRequest {

    private String title;
    private String description;
    private String externalReference;
    private String notificationUrl;
    private BigDecimal totalAmount;
    private List<CreateOrderItemRequest> items;

}

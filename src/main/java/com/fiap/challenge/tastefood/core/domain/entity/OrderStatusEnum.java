package com.fiap.challenge.tastefood.core.domain.entity;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public enum OrderStatusEnum {

    RECEBIDO, EM_PREPARACAO, CANCELADO, PRONTO;

    private static final Map<OrderStatusEnum, List<OrderStatusEnum>> POSSIBLE_STATUS_UPDATES = Map.of(
            RECEBIDO, List.of(EM_PREPARACAO, CANCELADO),
            EM_PREPARACAO, List.of(PRONTO)
    );

    public boolean isPossibleStatusUpdate(OrderStatusEnum status) {
        return POSSIBLE_STATUS_UPDATES.getOrDefault(this, Collections.emptyList()).contains(status);
    }

}

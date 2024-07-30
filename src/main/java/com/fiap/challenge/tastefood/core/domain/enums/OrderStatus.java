package com.fiap.challenge.tastefood.core.domain.enums;

import java.util.List;
import java.util.Map;

public enum OrderStatus {

    CRIADO, RECEBIDO, EM_PREPARACAO, PRONTO, FINALIZADO, CANCELADO;

    private static final Map<OrderStatus, List<OrderStatus>> POSSIBLE_STATUS_UPDATES = Map.of(
            CRIADO, List.of(RECEBIDO, CANCELADO),
            RECEBIDO, List.of(EM_PREPARACAO),
            EM_PREPARACAO, List.of(PRONTO),
            PRONTO, List.of(FINALIZADO),
            FINALIZADO, List.of(),
            CANCELADO, List.of()
    );

    public boolean isPossibleStatusUpdate(OrderStatus status) {
        return POSSIBLE_STATUS_UPDATES.get(this).contains(status);
    }

}

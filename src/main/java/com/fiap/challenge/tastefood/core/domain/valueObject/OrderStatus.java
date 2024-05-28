package com.fiap.challenge.tastefood.core.domain.valueObject;

import java.util.Map;
import java.util.Objects;

public enum OrderStatus {

    CRIADO, RECEBIDO, EM_PREPARACAO, PRONTO, FINALIZADO, CANCELADO;

    private static final Map<OrderStatus, OrderStatus> POSSIBLE_STATUS_UPDATES = Map.of(
            CRIADO, RECEBIDO,
            RECEBIDO, EM_PREPARACAO,
            EM_PREPARACAO, PRONTO,
            PRONTO, FINALIZADO
    );

    public boolean isPossibleStatusUpdate(OrderStatus status) {
        return Objects.equals(POSSIBLE_STATUS_UPDATES.get(this), status)
                || (Objects.equals(this, CRIADO) && Objects.equals(status, CANCELADO));
    }

}

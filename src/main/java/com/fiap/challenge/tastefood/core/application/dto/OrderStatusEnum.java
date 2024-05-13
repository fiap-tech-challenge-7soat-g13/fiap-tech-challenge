package com.fiap.challenge.tastefood.core.application.dto;

import com.fiap.challenge.tastefood.core.domain.exception.InvalidDataException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
@Getter
@AllArgsConstructor
public enum OrderStatusEnum {

    RECEBIDO("RECEBIDO", "Recebido"),
    EM_PREPARACAO("EM_PREPARACAO", "Em preparacão"),
    PRONTO("PRONTO", "Pronto");

    private final String id;
    private final String name;

    public static OrderStatusEnum getById(String id) {
        return Arrays.stream(values()).filter(value -> value.getId().equals(id)).findFirst().orElseThrow();
    }

    public static boolean isValidUpdateStatus(OrderStatusEnum oldStatus, OrderStatusEnum newStatus) throws InvalidDataException {
        if (newStatus.equals(OrderStatusEnum.EM_PREPARACAO) && oldStatus.equals(OrderStatusEnum.RECEBIDO)) {
            log.info("Atualizando status do pedido de: {} para: {}!", oldStatus, newStatus);
            return true;
        } else if (newStatus.equals(OrderStatusEnum.PRONTO)) {
            log.info("Atualizando status do pedido de: {} para status {}!", oldStatus, newStatus);
            return true;
        }
        throw new InvalidDataException(String.format("Não é possível atualizar do status: %s para o status: %s!", oldStatus, newStatus));
    }

    public static boolean getValidOrderStatusEnum(String status) {
        try {
            OrderStatusEnum.getById(status.toUpperCase());
            return true;
        } catch (Exception e) {
            log.error(String.format("StatusOrder informado não existe: %s", e.getMessage()), e);
            return false;
        }
    }

    public static OrderStatusEnum getOrderStatusEnum(String statusOrder) throws InvalidDataException {
        if(OrderStatusEnum.getValidOrderStatusEnum(statusOrder))
            return OrderStatusEnum.valueOf(statusOrder.toUpperCase());
        else
            throw new InvalidDataException(String.format("StatusOrder informado não existe: %s!", statusOrder));
    }

}


package com.fiap.challenge.tastefood.core.applications.dtos;

import com.fiap.challenge.tastefood.core.domain.exception.OrderException;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public enum StatusOrderEnum {

    RECEBIDO("RECEBIDO", "Recebido"),
    EM_PREPARACAO("EM_PREPARACAO", "Em preparacão"),
    PRONTO("PRONTO", "Pronto");

    String id;
    String name;

    StatusOrderEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    StatusOrderEnum() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static StatusOrderEnum getById(String id) {
        return Arrays.stream(values()).filter((shippingCompany) -> shippingCompany.getId().equals(id)).findFirst().orElseThrow();
    }

    public static StatusOrderEnum getByName(String name) {
        return Arrays.stream(values()).filter((shippingCompany) -> shippingCompany.getName().equals(name)).findFirst().orElseThrow();
    }

    public static boolean isValidUpdateStatus(StatusOrderEnum oldStatus, StatusOrderEnum newStatus) throws OrderException {
        if (newStatus.equals(StatusOrderEnum.EM_PREPARACAO) && oldStatus.equals(StatusOrderEnum.RECEBIDO)) {
            log.info("Atualizando status do pedido de: %s para: %s!", oldStatus, newStatus);
            return true;
        } else if (newStatus.equals(StatusOrderEnum.PRONTO)) {
            log.info("Atualizando status do pedido de: %s para status %s!", oldStatus, newStatus);
            return true;
        }
        throw new OrderException(String.format("Não é possível atualizar do status: %s para o status: %s!", oldStatus, newStatus));
    }

    public static boolean getValidStatusOrderEnum(String status) {
        try {
            StatusOrderEnum.getById(status.toUpperCase());
            return true;
        } catch (Exception e) {
            log.error("StatusOrder informado não existe: " + e.getMessage());
            return false;
        }
    }

    public static StatusOrderEnum getStatusOrderEnum(String statusOrder) throws OrderException {
        if(StatusOrderEnum.getValidStatusOrderEnum(statusOrder))
            return StatusOrderEnum.valueOf(statusOrder.toUpperCase());
        else
            throw new OrderException(String.format("StatusOrder informado não existe: %s!", statusOrder));
    }

}


package com.fiap.challenge.tastefood.core.usecases.status;

import com.fiap.challenge.tastefood.core.domain.enums.OrderStatus;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StatusListUseCaseTest {

    private final StatusListUseCase statusListUseCase = new StatusListUseCase();

    @Test
    void shouldListAllStatuses() {

        List<String> expected = Arrays.stream(OrderStatus.values()).map(OrderStatus::name).toList();

        List<String> actual = statusListUseCase.execute();

        assertEquals(expected, actual);
    }

}
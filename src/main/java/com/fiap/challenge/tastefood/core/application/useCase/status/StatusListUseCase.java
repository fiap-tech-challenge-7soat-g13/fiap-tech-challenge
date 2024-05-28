package com.fiap.challenge.tastefood.core.application.useCase.status;

import com.fiap.challenge.tastefood.core.domain.valueObject.OrderStatus;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class StatusListUseCase {

    @Transactional
    public List<String> execute() {
        return Arrays.stream(OrderStatus.values()).map(OrderStatus::name).toList();
    }

}
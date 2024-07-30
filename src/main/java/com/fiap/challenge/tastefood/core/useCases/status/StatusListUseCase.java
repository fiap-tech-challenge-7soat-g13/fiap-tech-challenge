package com.fiap.challenge.tastefood.core.useCases.status;

import com.fiap.challenge.tastefood.core.domain.enums.OrderStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class StatusListUseCase {

    public List<String> execute() {
        return Arrays.stream(OrderStatus.values()).map(OrderStatus::name).toList();
    }

}
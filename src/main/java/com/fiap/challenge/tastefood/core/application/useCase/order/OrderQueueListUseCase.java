package com.fiap.challenge.tastefood.core.application.useCase.order;

import com.fiap.challenge.tastefood.core.application.dto.OrderResponse;
import com.fiap.challenge.tastefood.core.application.mapper.OrderResponseMapper;
import com.fiap.challenge.tastefood.core.domain.repository.OrderRepository;
import com.fiap.challenge.tastefood.core.domain.valueObject.OrderStatus;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderQueueListUseCase {

    private final OrderResponseMapper mapper;
    private final OrderRepository repository;

    @Transactional
    public List<OrderResponse> execute(List<String> orderStatus) {
        return mapper.map(repository.findAllByStatusInOrderByCreatedAt(orderStatus));
    }

}

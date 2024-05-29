package com.fiap.challenge.tastefood.core.application.useCase.order;

import com.fiap.challenge.tastefood.core.application.mapper.OrderOutputMapper;
import com.fiap.challenge.tastefood.core.application.vo.OrderOutput;
import com.fiap.challenge.tastefood.core.domain.repository.OrderRepository;
import com.fiap.challenge.tastefood.core.domain.valueObject.OrderStatus;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderListUseCase {

    private final OrderOutputMapper mapper;
    private final OrderRepository repository;

    @Transactional
    public List<OrderOutput> execute(OrderStatus status) {
        return mapper.toOrderOutput(status == null ? repository.findAll() : repository.findByStatus(status));
    }

}

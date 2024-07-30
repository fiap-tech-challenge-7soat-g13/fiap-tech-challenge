package com.fiap.challenge.tastefood.core.useCases.order;

import com.fiap.challenge.tastefood.app.adapter.output.persistence.mapper.OrderMapper;
import com.fiap.challenge.tastefood.core.domain.enums.OrderStatus;
import com.fiap.challenge.tastefood.core.domain.Order;
import com.fiap.challenge.tastefood.core.gateways.OrderGateway;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderListUseCase {

    private final OrderMapper mapper;
    private final OrderGateway orderGateway;

    @Transactional
    public List<Order> execute(OrderStatus status) {
        return mapper.toOrder(status == null ? orderGateway.findAll() : orderGateway.findByStatus(status));
    }

}

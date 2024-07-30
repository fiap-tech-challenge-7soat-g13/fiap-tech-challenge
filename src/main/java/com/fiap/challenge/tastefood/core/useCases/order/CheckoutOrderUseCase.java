package com.fiap.challenge.tastefood.core.useCases.order;

import com.fiap.challenge.tastefood.app.adapter.output.persistence.entity.OrderEntity;
import com.fiap.challenge.tastefood.app.adapter.output.persistence.externalApis.MercadoPago;
import com.fiap.challenge.tastefood.app.adapter.output.persistence.mapper.OrderMapper;
import com.fiap.challenge.tastefood.core.domain.enums.OrderStatus;
import com.fiap.challenge.tastefood.core.domain.Order;
import com.fiap.challenge.tastefood.core.gateways.OrderGateway;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CheckoutOrderUseCase {

    private final OrderMapper mapper;
    private final OrderGateway orderGateway;
    private final MercadoPago mercadoPago;

    @Transactional
    public Order execute(Long id) {
        OrderEntity entity = orderGateway.findById(id).orElseThrow();

        if (mercadoPago.payment()) {
            entity.setStatus(OrderStatus.RECEBIDO);
            return mapper.toOrder(orderGateway.save(entity));
        }

        return mapper.toOrder(entity);
    }

}

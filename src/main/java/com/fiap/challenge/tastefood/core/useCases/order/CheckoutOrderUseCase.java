package com.fiap.challenge.tastefood.core.useCases.order;

import com.fiap.challenge.tastefood.app.adapter.output.persistence.externalApis.MercadoPago;
import com.fiap.challenge.tastefood.app.adapter.output.persistence.mapper.OrderMapper;
import com.fiap.challenge.tastefood.core.domain.Order;
import com.fiap.challenge.tastefood.core.domain.enums.OrderStatus;
import com.fiap.challenge.tastefood.core.gateways.OrderGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CheckoutOrderUseCase {

    private final OrderMapper mapper;
    private final OrderGateway orderGateway;
    private final MercadoPago mercadoPago;

    public Order execute(Long id) {
        Order order = orderGateway.findById(id).orElseThrow();

        if (mercadoPago.payment()) {
            order.setStatus(OrderStatus.RECEBIDO);
            return orderGateway.save(order);
        }

        return order;
    }

}

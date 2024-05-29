package com.fiap.challenge.tastefood.core.application.useCase.order;

import com.fiap.challenge.tastefood.core.application.mapper.OrderOutputMapper;
import com.fiap.challenge.tastefood.core.application.vo.OrderOutput;
import com.fiap.challenge.tastefood.core.domain.entity.Order;
import com.fiap.challenge.tastefood.core.domain.externalApis.MercadoPago;
import com.fiap.challenge.tastefood.core.domain.repository.OrderRepository;
import com.fiap.challenge.tastefood.core.domain.valueObject.OrderStatus;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CheckoutOrderUseCase {

    private final OrderOutputMapper mapper;
    private final OrderRepository repository;
    private final MercadoPago mercadoPago;

    @Transactional
    public OrderOutput execute(Long id) {
        Order entity = repository.findById(id).orElseThrow();

        if (mercadoPago.payment()) {
            entity.setStatus(OrderStatus.RECEBIDO);
            return mapper.toOrderOutput(repository.save(entity));
        }

        return mapper.toOrderOutput(entity);
    }

}

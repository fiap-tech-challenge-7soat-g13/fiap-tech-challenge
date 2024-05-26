package com.fiap.challenge.tastefood.core.application.useCase.order;

import com.fiap.challenge.tastefood.core.domain.entity.Order;
import com.fiap.challenge.tastefood.core.domain.valueObject.OrderStatus;
import com.fiap.challenge.tastefood.core.domain.repository.OrderRepository;
import com.fiap.challenge.tastefood.core.application.validator.OrderUpdateStatusValidator;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderUpdateStatusUseCase {

    private final OrderRepository repository;

    private final OrderUpdateStatusValidator validator;

    @Transactional
    public void execute(Long id, OrderStatus status) {

        validator.validate(id, status);

        Order order = repository.findById(id).orElseThrow();
        order.setStatus(status);
        repository.save(order);
    }

}

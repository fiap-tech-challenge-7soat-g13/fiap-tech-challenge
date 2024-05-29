package com.fiap.challenge.tastefood.core.application.useCase.order;

import com.fiap.challenge.tastefood.core.application.mapper.OrderInputMapper;
import com.fiap.challenge.tastefood.core.application.validator.OrderCreateValidator;
import com.fiap.challenge.tastefood.core.application.vo.OrderInput;
import com.fiap.challenge.tastefood.core.domain.entity.Order;
import com.fiap.challenge.tastefood.core.domain.entity.OrderProduct;
import com.fiap.challenge.tastefood.core.domain.repository.OrderRepository;
import com.fiap.challenge.tastefood.core.domain.valueObject.OrderStatus;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class OrderCreateUseCase {

    private final OrderInputMapper mapper;
    private final OrderRepository repository;
    private final OrderCreateValidator validator;

    @Transactional
    public Long execute(OrderInput orderInput) {

        validator.validate(orderInput);

        Order entity = mapper.map(orderInput);

        entity.setCreatedAt(LocalDateTime.now());
        entity.setStatus(OrderStatus.CRIADO);
        entity.setTotal(BigDecimal.ZERO);

        for (OrderProduct orderProduct : entity.getProducts()) {
            orderProduct.setOrder(entity);
            orderProduct.setPrice(orderProduct.getProduct().getPrice());
            entity.setTotal(entity.getTotal().add(BigDecimal.valueOf(orderProduct.getQuantity()).multiply(orderProduct.getPrice())));
        }

        Order saved = repository.save(entity);

        return saved.getId();
    }

}

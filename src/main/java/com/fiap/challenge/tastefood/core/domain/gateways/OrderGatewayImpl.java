package com.fiap.challenge.tastefood.core.domain.gateways;

import com.fiap.challenge.tastefood.adapter.driven.infra.OrderGateway;
import com.fiap.challenge.tastefood.core.domain.entities.OrderEntity;
import com.fiap.challenge.tastefood.core.domain.entities.StatusOrderEnum;
import com.fiap.challenge.tastefood.core.domain.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class OrderGatewayImpl implements OrderGateway {

    private final OrderRepository orderRepository;

    public List<OrderEntity> findAll() {
        return (List<OrderEntity>) orderRepository.findAll();
    }

    public Long create(OrderEntity orderEntity) {
        orderRepository.save(orderEntity);
        log.info("Pedido criado com sucesso!");
        Optional<OrderEntity> orderId = orderRepository.findById(orderEntity.getId());
        return orderId.get().getId();
    }

    public List<OrderEntity> findByStatus(StatusOrderEnum status) {
        try {
            Optional<List<OrderEntity>> listOrderRepositoryDbs = orderRepository.findByStatus(status);
            return listOrderRepositoryDbs.orElseGet(ArrayList::new);
        } catch (Exception e) {
            log.error("Erro ao buscar pedidos para no status: " + status);
            return new ArrayList<>();
        }
    }

    public OrderEntity findbyId(Long idOrder) {
        Optional<OrderEntity> orderRepositoryDb = orderRepository.findById(idOrder);
        return orderRepositoryDb.orElseGet(OrderEntity::new);
    }

    public OrderEntity updateStatusOrder(Long id, StatusOrderEnum statusOrderEnum) {
        Optional<OrderEntity> order = orderRepository.findById(id);
        if (order.isPresent()) {
            order.get().setStatus(StatusOrderEnum.getById(statusOrderEnum.getId()));
            return orderRepository.save(order.get());
        }
        return null;
    }

    public OrderEntity update(OrderEntity orderEntity) {
        return orderRepository.save(orderEntity);
    }

}

package com.fiap.challenge.tastefood.core.domain.gateways;

import com.fiap.challenge.tastefood.adapter.driven.infra.OrderGateway;
import com.fiap.challenge.tastefood.core.domain.entities.OrderEntity;
import com.fiap.challenge.tastefood.core.domain.entities.StatusOrderEnum;
import com.fiap.challenge.tastefood.core.domain.repositories.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderGatewayImpl implements OrderGateway {

    private final OrderRepository orderRepository;
    private static final Logger logger = LoggerFactory.getLogger(OrderGatewayImpl.class);

    @Autowired
    public OrderGatewayImpl(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public List<OrderEntity> findAll() {
        return (List<OrderEntity>) orderRepository.findAll();
    }

    public Long create(OrderEntity orderEntity) {
        orderRepository.save(orderEntity);
        logger.info("Pedido criado com sucesso!");
        Optional<OrderEntity> orderId = orderRepository.findById(orderEntity.getId());
        return orderId.get().getId();
    }

    public List<OrderEntity> findByStatus(StatusOrderEnum status) {
        try {
            Optional<List<OrderEntity>> listOrderRepositoryDbs = orderRepository.findByStatusOrderEnum(status);
            return listOrderRepositoryDbs.orElseGet(ArrayList::new);
        } catch (Exception e) {
            logger.error("Erro ao buscar pedidos para no status: " + status);
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
            order.get().setStatusOrderEnum(com.fiap.challenge.tastefood.core.domain.entities.StatusOrderEnum.getById(statusOrderEnum.getId()));
            return orderRepository.save(order.get());
        }
        return null;
    }

    public OrderEntity update(OrderEntity orderEntity) {
        return orderRepository.save(orderEntity);
    }

}

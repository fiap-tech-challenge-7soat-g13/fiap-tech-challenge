package com.fiap.challenge.tastefood.core.domain.repository;

import com.fiap.challenge.tastefood.core.domain.entity.OrderEntity;
import com.fiap.challenge.tastefood.core.domain.entity.OrderStatusEnum;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends CrudRepository<OrderEntity, Long> {

    Optional<List<OrderEntity>> findByStatus(OrderStatusEnum status);

}

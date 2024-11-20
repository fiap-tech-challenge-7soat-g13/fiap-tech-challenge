package com.fiap.challenge.tastefood.app.adapter.output.persistence.repository;

import com.fiap.challenge.tastefood.app.adapter.output.persistence.entity.OrderEntity;
import com.fiap.challenge.tastefood.core.domain.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    OrderEntity save(OrderEntity orderEntity);

    Optional<OrderEntity> findById(Long id);

    List<OrderEntity> findAll();

    List<OrderEntity> findByStatus(OrderStatus status);

    List<OrderEntity> findAllByStatusInOrderByStatusDesc(List<OrderStatus> orderStatus);

}

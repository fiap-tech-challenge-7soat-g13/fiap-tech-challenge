package com.fiap.challenge.tastefood.app.adapter.output.persistence.repository;

import com.fiap.challenge.tastefood.app.adapter.output.persistence.entity.OrderEntity;
import com.fiap.challenge.tastefood.app.adapter.output.persistence.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {

    OrderEntity save(OrderEntity orderEntity);

    Optional<PaymentEntity> findById(Long id);

    Optional<PaymentEntity> findByOrderId(Long orderId);

}

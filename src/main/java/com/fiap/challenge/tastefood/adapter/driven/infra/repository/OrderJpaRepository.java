package com.fiap.challenge.tastefood.adapter.driven.infra.repository;

import com.fiap.challenge.tastefood.core.domain.entity.Order;
import com.fiap.challenge.tastefood.core.domain.repository.OrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderJpaRepository extends OrderRepository, JpaRepository<Order, Long> {

}

package com.fiap.challenge.tastefood.core.domain.repositories;

import com.fiap.challenge.tastefood.core.domain.entities.OrderEntity;
import com.fiap.challenge.tastefood.core.domain.entities.StatusOrderEnum;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends CrudRepository<OrderEntity, Long> {

    Optional<List<OrderEntity>> findByStatus(StatusOrderEnum status);

}

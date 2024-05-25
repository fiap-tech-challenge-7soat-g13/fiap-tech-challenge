package com.fiap.challenge.tastefood.adapter.driven.infra.repository;

import com.fiap.challenge.tastefood.core.domain.entity.Customer;
import com.fiap.challenge.tastefood.core.domain.repository.CustomerRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerJpaRepository extends CustomerRepository, JpaRepository<Customer, Long> {

}

package com.fiap.challenge.tastefood.core.domain.repository;

import com.fiap.challenge.tastefood.core.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByDocument(String document);

}

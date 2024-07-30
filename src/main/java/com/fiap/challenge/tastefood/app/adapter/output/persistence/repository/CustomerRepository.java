package com.fiap.challenge.tastefood.app.adapter.output.persistence.repository;

import com.fiap.challenge.tastefood.app.adapter.output.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    CustomerEntity save(CustomerEntity customerEntity);

    Optional<CustomerEntity> findById(Long id);

    List<CustomerEntity> findAll();

    List<CustomerEntity> findByDocument(String document);

}

package com.fiap.challenge.tastefood.core.application.useCase.customer;

import com.fiap.challenge.tastefood.core.application.dto.CustomerResponse;
import com.fiap.challenge.tastefood.core.application.mapper.CustomerResponseMapper;
import com.fiap.challenge.tastefood.core.domain.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerListUseCase {

    private final CustomerResponseMapper mapper;
    private final CustomerRepository repository;

    @Transactional
    public List<CustomerResponse> execute(String document) {
        return mapper.map(StringUtils.isBlank(document) ? repository.findAll() : repository.findByDocument(document));
    }

}
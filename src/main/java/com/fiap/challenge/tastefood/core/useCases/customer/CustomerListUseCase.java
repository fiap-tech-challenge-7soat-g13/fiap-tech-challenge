package com.fiap.challenge.tastefood.core.useCases.customer;

import com.fiap.challenge.tastefood.app.adapter.output.persistence.mapper.CustomerMapper;
import com.fiap.challenge.tastefood.core.domain.Customer;
import com.fiap.challenge.tastefood.core.gateways.CustomerGateway;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerListUseCase {

    private final CustomerMapper mapper;
    private final CustomerGateway customerGateway;

    @Transactional
    public List<Customer> execute(String document) {
        return mapper.toCustomer(StringUtils.isBlank(document) ? customerGateway.findAll()
                : customerGateway.findByDocument(document));
    }

}
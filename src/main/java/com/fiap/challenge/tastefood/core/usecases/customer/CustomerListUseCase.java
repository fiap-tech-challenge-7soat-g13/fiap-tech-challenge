package com.fiap.challenge.tastefood.core.usecases.customer;

import com.fiap.challenge.tastefood.core.domain.Customer;
import com.fiap.challenge.tastefood.core.gateways.CustomerGateway;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerListUseCase {

    private final CustomerGateway customerGateway;

    public List<Customer> execute(String document) {
        return StringUtils.isBlank(document) ? customerGateway.findAll() : customerGateway.findByDocument(document);
    }

}
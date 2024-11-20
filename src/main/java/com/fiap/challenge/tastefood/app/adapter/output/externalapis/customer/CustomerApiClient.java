package com.fiap.challenge.tastefood.app.adapter.output.externalapis.customer;

import com.fiap.challenge.tastefood.app.adapter.output.externalapis.CustomerClient;
import com.fiap.challenge.tastefood.core.domain.Customer;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerApiClient implements CustomerClient {

    private final CustomerApiFeignClient client;
    private final GetCustomerResponseMapper mapper;

    @Override
    public Optional<Customer> getCustomer(Long id) {
        try {
            return Optional.of(mapper.toCustomer(client.getCustomer(id)));
        } catch (FeignException.NotFound e) {
            return Optional.empty();
        }
    }

}

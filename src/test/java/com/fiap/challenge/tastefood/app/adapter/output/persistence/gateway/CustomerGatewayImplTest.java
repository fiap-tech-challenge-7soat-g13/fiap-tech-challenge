package com.fiap.challenge.tastefood.app.adapter.output.persistence.gateway;

import com.fiap.challenge.tastefood.app.adapter.output.externalapis.CustomerClient;
import com.fiap.challenge.tastefood.core.domain.Customer;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CustomerGatewayImplTest {

    private final CustomerClient customerClient = mock(CustomerClient.class);

    private final CustomerGatewayImpl customerGateway = new CustomerGatewayImpl(customerClient);

    @Test
    void shouldFindById() {

        UUID id = UUID.fromString("670104bb-eac6-4bb1-ae7f-df2cdd60d9ba");

        Optional<Customer> expected = Optional.of(new Customer());

        when(customerClient.getCustomer(id)).thenReturn(expected);

        Optional<Customer> actual = customerGateway.findById(id);

        verify(customerClient).getCustomer(id);

        assertEquals(expected, actual);
    }

}
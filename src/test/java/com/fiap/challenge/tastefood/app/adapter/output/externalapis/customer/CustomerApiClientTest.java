package com.fiap.challenge.tastefood.app.adapter.output.externalapis.customer;

import com.fiap.challenge.tastefood.core.domain.Customer;
import feign.FeignException;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CustomerApiClientTest {

    private final CustomerApiFeignClient customerApiFeignClient = mock(CustomerApiFeignClient.class);
    private final GetCustomerResponseMapper getCustomerResponseMapper = mock(GetCustomerResponseMapper.class);

    private final CustomerApiClient customerApiClient = new CustomerApiClient(customerApiFeignClient, getCustomerResponseMapper);

    @Test
    void shouldReturnCustomer() {

        Long id = 1L;

        GetCustomerResponse getCustomerResponse = new GetCustomerResponse();
        Customer customer = new Customer();

        when(customerApiFeignClient.getCustomer(id)).thenReturn(getCustomerResponse);
        when(getCustomerResponseMapper.toCustomer(getCustomerResponse)).thenReturn(customer);

        Optional<Customer> actual = customerApiClient.getCustomer(id);

        verify(customerApiFeignClient).getCustomer(id);
        verify(getCustomerResponseMapper).toCustomer(getCustomerResponse);

        assertEquals(Optional.of(customer), actual);
    }

    @Test
    void shouldNotReturnCustomer() {

        Long id = 1L;

        when(customerApiFeignClient.getCustomer(id)).thenThrow(FeignException.NotFound.class);

        Optional<Customer> actual = customerApiClient.getCustomer(id);

        verify(customerApiFeignClient).getCustomer(id);
        verify(getCustomerResponseMapper, never()).toCustomer(any());

        assertEquals(Optional.empty(), actual);
    }

}
package com.fiap.challenge.tastefood.app.adapter.output.externalapis.customer;

import com.fiap.challenge.tastefood.core.domain.Customer;
import feign.FeignException;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CustomerApiClientTest {

    private final CustomerApiFeignClient customerApiFeignClient = mock(CustomerApiFeignClient.class);
    private final GetCustomerResponseMapper getCustomerResponseMapper = mock(GetCustomerResponseMapper.class);

    private final CustomerApiClient customerApiClient = new CustomerApiClient(customerApiFeignClient, getCustomerResponseMapper);

    @Test
    void shouldReturnEmptyCustomer() {

        UUID id = UUID.fromString("670104bb-eac6-4bb1-ae7f-df2cdd60d9ba");

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
    void shouldReturnCustomer() {

        UUID id = UUID.fromString("670104bb-eac6-4bb1-ae7f-df2cdd60d9ba");
        GetCustomerResponse getCustomerResponse = new GetCustomerResponse(id, "Nome de Teste",
                "teste@teste.com.br", "01234567890");
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

        UUID id = UUID.fromString("670104bb-eac6-4bb1-ae7f-df2cdd60d9ba");

        when(customerApiFeignClient.getCustomer(id)).thenThrow(FeignException.NotFound.class);

        Optional<Customer> actual = customerApiClient.getCustomer(id);

        verify(customerApiFeignClient).getCustomer(id);
        verify(getCustomerResponseMapper, never()).toCustomer(any());

        assertEquals(Optional.empty(), actual);
    }

}
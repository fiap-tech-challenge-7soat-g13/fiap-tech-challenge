package com.fiap.challenge.tastefood.core.usecases.customer;

import com.fiap.challenge.tastefood.core.domain.Customer;
import com.fiap.challenge.tastefood.core.gateways.CustomerGateway;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CustomerListUseCaseTest {

    private final CustomerGateway customerGateway = mock(CustomerGateway.class);

    private final CustomerListUseCase customerListUseCase = new CustomerListUseCase(customerGateway);

    @Test
    void shouldListAll() {

        List<Customer> expected = List.of(new Customer(), new Customer());

        when(customerGateway.findAll()).thenReturn(expected);

        List<Customer> actual = customerListUseCase.execute(null);

        verify(customerGateway).findAll();
        verify(customerGateway, never()).findByDocument(any());

        assertEquals(expected, actual);
    }

    @Test
    void shouldListByDocument() {

        String document = "document";
        List<Customer> expected = List.of(new Customer(), new Customer());

        when(customerGateway.findByDocument(document)).thenReturn(expected);

        List<Customer> actual = customerListUseCase.execute(document);

        verify(customerGateway).findByDocument(any());
        verify(customerGateway, never()).findAll();

        assertEquals(expected, actual);
    }

}
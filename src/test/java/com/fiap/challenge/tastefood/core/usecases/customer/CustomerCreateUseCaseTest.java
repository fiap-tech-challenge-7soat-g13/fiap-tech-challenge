package com.fiap.challenge.tastefood.core.usecases.customer;

import com.fiap.challenge.tastefood.core.common.validator.CustomerCreateValidator;
import com.fiap.challenge.tastefood.core.domain.Customer;
import com.fiap.challenge.tastefood.core.gateways.CustomerGateway;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CustomerCreateUseCaseTest {

    private final CustomerGateway customerGateway = mock(CustomerGateway.class);

    private final CustomerCreateValidator customerCreateValidator = mock(CustomerCreateValidator.class);

    private final CustomerCreateUseCase customerCreateUseCase = new CustomerCreateUseCase(customerGateway, customerCreateValidator);

    @Test
    void shouldSaveProduct() {

        Customer customer = new Customer();

        customerCreateUseCase.execute(customer);

        verify(customerGateway).save(customer);
    }

}
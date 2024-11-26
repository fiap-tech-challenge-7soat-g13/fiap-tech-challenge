package com.fiap.challenge.tastefood.app.adapter.output.persistence.gateway;

import com.fiap.challenge.tastefood.app.adapter.output.externalapis.PaymentClient;
import com.fiap.challenge.tastefood.core.domain.Payment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PaymentGatewayImplTest {

    private final PaymentClient paymentClient = mock(PaymentClient.class);

    private final PaymentGatewayImpl paymentGateway = new PaymentGatewayImpl(paymentClient);

    @Test
    void shouldFindById() {

        Long id = 1L;

        Payment payment = new Payment();

        when(paymentClient.getPayment(id)).thenReturn(payment);

        Payment actual = paymentGateway.findById(id);

        verify(paymentClient).getPayment(id);

        assertEquals(payment, actual);
    }

}

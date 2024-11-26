package com.fiap.challenge.tastefood.app.adapter.output.externalapis.payment;

import com.fiap.challenge.tastefood.core.domain.Order;
import com.fiap.challenge.tastefood.core.domain.Payment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PaymentApiClientTest {

    private final PaymentApiFeignClient paymentApiFeignClient = mock(PaymentApiFeignClient.class);
    private final GetPaymentResponseMapper getPaymentResponseMapper = mock(GetPaymentResponseMapper.class);
    private final CreatePaymentOrderRequestMapper orderRequestMapper = mock(CreatePaymentOrderRequestMapper.class);

    private final PaymentApiClient paymentApiClient = new PaymentApiClient(paymentApiFeignClient, getPaymentResponseMapper, orderRequestMapper);

    @Test
    void shouldCreatePayment() {

        Order order = new Order();
        CreatePaymentOrderRequest createPaymentOrderRequest = new CreatePaymentOrderRequest();
        GetPaymentResponse getPaymentResponse = new GetPaymentResponse();
        Payment expected = new Payment();

        when(orderRequestMapper.toCreatePaymentOrderRequest(order)).thenReturn(createPaymentOrderRequest);
        when(paymentApiFeignClient.create(createPaymentOrderRequest)).thenReturn(getPaymentResponse);
        when(getPaymentResponseMapper.toPayment(getPaymentResponse)).thenReturn(expected);

        Payment actual = paymentApiClient.createPayment(order);

        verify(orderRequestMapper).toCreatePaymentOrderRequest(order);
        verify(paymentApiFeignClient).create(createPaymentOrderRequest);
        verify(getPaymentResponseMapper).toPayment(getPaymentResponse);

        assertEquals(expected, actual);
    }

    @Test
    void shouldGetPayment() {

        Long id = 1L;

        GetPaymentResponse getPaymentResponse = new GetPaymentResponse();
        Payment expected = new Payment();

        when(paymentApiFeignClient.getPayment(id)).thenReturn(getPaymentResponse);
        when(getPaymentResponseMapper.toPayment(getPaymentResponse)).thenReturn(expected);

        Payment actual = paymentApiClient.getPayment(id);

        verify(paymentApiFeignClient).getPayment(id);
        verify(getPaymentResponseMapper).toPayment(getPaymentResponse);

        assertEquals(expected, actual);
    }

}

package com.fiap.challenge.tastefood.core.gateways;

import com.fiap.challenge.tastefood.core.domain.Payment;

public interface PaymentGateway {

    Payment findById(Long id);

}

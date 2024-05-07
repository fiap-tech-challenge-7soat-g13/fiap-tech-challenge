package com.fiap.challenge.tastefood.core.domain.mapper;

import com.fiap.challenge.tastefood.adapter.driver.formsDto.CheckoutOrderFormDto;
import com.fiap.challenge.tastefood.core.applications.dtos.Checkout;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-07T01:38:28-0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 21 (Azul Systems, Inc.)"
)
@Component
public class CheckoutMapperImpl implements CheckoutMapper {

    @Override
    public Checkout fromCheckoutOrderFormDto(CheckoutOrderFormDto checkoutOrderFormDto) {
        if ( checkoutOrderFormDto == null ) {
            return null;
        }

        Checkout checkout = new Checkout();

        return checkout;
    }
}

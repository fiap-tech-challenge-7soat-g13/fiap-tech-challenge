package com.fiap.challenge.tastefood.core.domain.mapper;

import com.fiap.challenge.tastefood.adapter.driver.formsDto.CheckoutOrderFormDto;
import com.fiap.challenge.tastefood.core.applications.dtos.Checkout;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true), 
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
public interface CheckoutMapper {
    
    Checkout fromCheckoutOrderFormDto(CheckoutOrderFormDto checkoutOrderFormDto);

}

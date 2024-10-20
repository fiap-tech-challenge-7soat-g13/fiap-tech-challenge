package com.fiap.challenge.tastefood.core.usecases.product;

import com.fiap.challenge.tastefood.core.domain.Product;
import com.fiap.challenge.tastefood.core.gateways.ProductGateway;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

class ProductRemoveUseCaseTest {

    private final ProductGateway productGateway = mock(ProductGateway.class);

    private final ProductRemoveUseCase productRemoveUseCase = new ProductRemoveUseCase(productGateway);

    @Test
    void shouldSaveProduct() {

        Long id = 1L;

        Product product = new Product();

        when(productGateway.findById(id)).thenReturn(Optional.of(product));

        productRemoveUseCase.execute(id);

        verify(productGateway).save(product);

        assertFalse(product.getActive());
    }

}
package com.fiap.challenge.tastefood.core.usecases.product;

import com.fiap.challenge.tastefood.core.common.validator.ProductUpdateValidator;
import com.fiap.challenge.tastefood.core.domain.Product;
import com.fiap.challenge.tastefood.core.gateways.ProductGateway;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ProductUpdateUseCaseTest {

    private final ProductGateway productGateway = mock(ProductGateway.class);

    private final ProductUpdateValidator validator = mock(ProductUpdateValidator.class);

    private final ProductUpdateUseCase productUpdateUseCase = new ProductUpdateUseCase(productGateway, validator);

    @Test
    void shouldSaveProduct() {

        Product product = new Product();

        productUpdateUseCase.execute(product);

        verify(productGateway).update(product);
    }

}
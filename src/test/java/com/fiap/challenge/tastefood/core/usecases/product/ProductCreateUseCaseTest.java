package com.fiap.challenge.tastefood.core.usecases.product;

import com.fiap.challenge.tastefood.core.common.validator.ProductCreateValidator;
import com.fiap.challenge.tastefood.core.domain.Product;
import com.fiap.challenge.tastefood.core.gateways.ProductGateway;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ProductCreateUseCaseTest {

    private final ProductGateway productGateway = mock(ProductGateway.class);

    private final ProductCreateValidator productCreateValidator = mock(ProductCreateValidator.class);

    private final ProductCreateUseCase productCreateUseCase = new ProductCreateUseCase(productGateway, productCreateValidator);

    @Test
    void shouldSaveProduct() {

        Product product = new Product();

        productCreateUseCase.execute(product);

        verify(productGateway).save(product);

        assertTrue(product.getActive());
    }

}
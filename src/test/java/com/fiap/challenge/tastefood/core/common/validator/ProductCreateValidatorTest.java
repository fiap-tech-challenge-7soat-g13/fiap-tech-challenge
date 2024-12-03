package com.fiap.challenge.tastefood.core.common.validator;

import com.fiap.challenge.tastefood.core.domain.Product;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ProductCreateValidatorTest {

    private final ProductUpdateValidator productUpdateValidator = mock(ProductUpdateValidator.class);

    private final ProductCreateValidator productCreateValidator = new ProductCreateValidator(productUpdateValidator);

    @Test
    void shouldCallProductUpdateValidator() {

        Product product = new Product();

        productCreateValidator.validate(product);

        verify(productUpdateValidator).validate(product);
    }

}
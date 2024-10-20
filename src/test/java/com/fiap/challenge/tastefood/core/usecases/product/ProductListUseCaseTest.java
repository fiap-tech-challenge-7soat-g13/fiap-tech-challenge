package com.fiap.challenge.tastefood.core.usecases.product;

import com.fiap.challenge.tastefood.core.domain.Product;
import com.fiap.challenge.tastefood.core.domain.enums.ProductCategory;
import com.fiap.challenge.tastefood.core.gateways.ProductGateway;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductListUseCaseTest {

    private final ProductGateway productGateway = mock(ProductGateway.class);

    private final ProductListUseCase productListUseCase = new ProductListUseCase(productGateway);

    @Test
    void shouldListAll() {

        List<Product> expected = List.of(new Product(), new Product());

        when(productGateway.findByActiveTrue()).thenReturn(expected);

        List<Product> actual = productListUseCase.execute(null);

        verify(productGateway).findByActiveTrue();
        verify(productGateway, never()).findByCategoryAndActiveTrue(any());

        assertEquals(expected, actual);
    }

    @Test
    void shouldListByCategory() {

        ProductCategory category = ProductCategory.BEBIDA;
        List<Product> expected = List.of(new Product(), new Product());

        when(productGateway.findByCategoryAndActiveTrue(category)).thenReturn(expected);

        List<Product> actual = productListUseCase.execute(category);

        verify(productGateway).findByCategoryAndActiveTrue(any());
        verify(productGateway, never()).findByActiveTrue();

        assertEquals(expected, actual);
    }

}
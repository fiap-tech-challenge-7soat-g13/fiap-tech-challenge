package com.fiap.challenge.tastefood.app.adapter.output.persistence.gateway;

import com.fiap.challenge.tastefood.app.adapter.output.persistence.entity.ProductEntity;
import com.fiap.challenge.tastefood.app.adapter.output.persistence.mapper.ProductMapper;
import com.fiap.challenge.tastefood.app.adapter.output.persistence.repository.ProductRepository;
import com.fiap.challenge.tastefood.core.domain.Product;
import com.fiap.challenge.tastefood.core.domain.enums.ProductCategory;
import com.fiap.challenge.tastefood.util.DataHelper;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductGatewayImplTest {

    private final ProductMapper productMapper = mock(ProductMapper.class);
    private final ProductRepository repository = mock(ProductRepository.class);

    private ProductGatewayImpl productGateway = new ProductGatewayImpl(productMapper, repository);

    @Test
    void shouldSave() {
        Long id = 1L;
        Product product = DataHelper.getProductMock(null);
        ProductEntity productEntity = DataHelper.getProductEntityMock(id);
        Product productWithId = DataHelper.getProductMock(id);

        when(productMapper.toProductEntity(product)).thenReturn(productEntity);
        when(repository.save(any(ProductEntity.class))).thenReturn(productEntity);
        when(productMapper.toProduct(productEntity)).thenReturn(productWithId);

        Product response = productGateway.save(product);

        verify(productMapper).toProductEntity(any(Product.class));
        verify(repository).save(any(ProductEntity.class));
        verify(productMapper).toProduct(any(ProductEntity.class));

        assertNotNull(response);
        assertNotNull(response.getId());
        assertEquals(id, response.getId());
    }

    @Test
    void shouldFindById() {
        Long id = 1L;
        ProductEntity productEntity = DataHelper.getProductEntityMock(id);
        Product productWithId = DataHelper.getProductMock(id);

        when(repository.findById(anyLong())).thenReturn(Optional.of(productEntity));
        when(productMapper.toProduct(productEntity)).thenReturn(productWithId);

        Optional<Product> response = productGateway.findById(id);

        verify(repository).findById(anyLong());
        verify(productMapper).toProduct(any(ProductEntity.class));

        assertNotNull(response);
        assertTrue(response.isPresent());
    }

    @Test
    void shouldFindByActiveTrue() {
        Long id = 1L;
        ProductEntity productEntity = DataHelper.getProductEntityMock(id);
        Product productWithId = DataHelper.getProductMock(id);

        when(repository.findByActiveTrue()).thenReturn(List.of(productEntity));
        when(productMapper.toProduct(anyList())).thenReturn(List.of(productWithId));

        List<Product> response = productGateway.findByActiveTrue();

        verify(repository).findByActiveTrue();
        verify(productMapper).toProduct(anyList());

        assertNotNull(response);
        assertFalse(response.isEmpty());
    }

    @Test
    void shouldFindByCategoryAndActiveTrue() {
        Long id = 1L;
        ProductEntity productEntity = DataHelper.getProductEntityMock(id);
        Product productWithId = DataHelper.getProductMock(id);

        when(repository.findByCategoryAndActiveTrue(any(ProductCategory.class))).thenReturn(List.of(productEntity));
        when(productMapper.toProduct(anyList())).thenReturn(List.of(productWithId));

        List<Product> response = productGateway.findByCategoryAndActiveTrue(ProductCategory.LANCHE);

        verify(repository).findByCategoryAndActiveTrue(any(ProductCategory.class));
        verify(productMapper).toProduct(anyList());

        assertNotNull(response);
        assertFalse(response.isEmpty());
    }

    @Test
    void shouldFindByNameAndActiveTrue() {
        Long id = 1L;
        ProductEntity productEntity = DataHelper.getProductEntityMock(id);
        Product productWithId = DataHelper.getProductMock(id);

        when(repository.findByNameAndActiveTrue("")).thenReturn(Optional.of(productEntity));
        when(productMapper.toProduct(any(ProductEntity.class))).thenReturn(productWithId);

        Optional<Product> response = productGateway.findByNameAndActiveTrue("");

        verify(repository).findByNameAndActiveTrue(anyString());
        verify(productMapper).toProduct(any(ProductEntity.class));

        assertNotNull(response);
        assertTrue(response.isPresent());
    }

    @Test
    void shouldUpdate() {
        Long id = 1L;
        Product product = DataHelper.getProductMock(id);
        product.setName("Atualizado");
        ProductEntity productEntity = DataHelper.getProductEntityMock(id);

        Product productSave = DataHelper.getProductMock(id);

        when(repository.findById(anyLong())).thenReturn(Optional.of(productEntity));
        when(productMapper.toProduct(productEntity)).thenReturn(product);
        doNothing().when(productMapper).update(product, productSave);
        when(productMapper.toProductEntity(product)).thenReturn(productEntity);
        when(repository.save(any(ProductEntity.class))).thenReturn(productEntity);
        when(productMapper.toProduct(productEntity)).thenReturn(product);

        Product response = productGateway.update(product);

        verify(repository).findById(anyLong());
        verify(productMapper).update(any(Product.class), any(Product.class));
        verify(productMapper).toProductEntity(any(Product.class));
        verify(repository).save(any(ProductEntity.class));
        verify(productMapper, times(2)).toProduct(any(ProductEntity.class));

        assertNotNull(response);
        assertNotNull(response.getId());
        assertEquals(id, response.getId());
    }

}

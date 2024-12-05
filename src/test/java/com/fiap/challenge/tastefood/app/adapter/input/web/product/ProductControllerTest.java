package com.fiap.challenge.tastefood.app.adapter.input.web.product;

import com.fiap.challenge.tastefood.app.adapter.input.web.product.dto.ProductRequest;
import com.fiap.challenge.tastefood.app.adapter.input.web.product.dto.ProductResponse;
import com.fiap.challenge.tastefood.app.adapter.input.web.product.mapper.ProductRequestMapper;
import com.fiap.challenge.tastefood.app.adapter.input.web.product.mapper.ProductResponseMapper;
import com.fiap.challenge.tastefood.core.domain.Product;
import com.fiap.challenge.tastefood.core.domain.enums.ProductCategory;
import com.fiap.challenge.tastefood.core.usecases.category.CategoryListUseCase;
import com.fiap.challenge.tastefood.core.usecases.product.ProductCreateUseCase;
import com.fiap.challenge.tastefood.core.usecases.product.ProductListUseCase;
import com.fiap.challenge.tastefood.core.usecases.product.ProductRemoveUseCase;
import com.fiap.challenge.tastefood.core.usecases.product.ProductUpdateUseCase;
import com.fiap.challenge.tastefood.util.DataHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProductControllerTest {

	@Mock
	private ProductCreateUseCase productCreateUseCase;
	@Mock
	private ProductUpdateUseCase productUpdateUseCase;
	@Mock
	private ProductRemoveUseCase productRemoveUseCase;
	@Mock
	private ProductListUseCase productListUseCase;
	@Mock
	private CategoryListUseCase categoryListUseCase;

	private final ProductRequestMapper productRequestMapper = mock(ProductRequestMapper.class);
	private final ProductResponseMapper productResponseMapper = mock(ProductResponseMapper.class);

	@InjectMocks
	private ProductController productController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void shouldSave() {
		ProductRequest productRequestMock = DataHelper.getProductRequestMock();
		Product expected = DataHelper.getProductMock(null);
		Long id = 1L;
		Product expectedWithId = DataHelper.getProductMock(id);
		ProductResponse orderResponseMock = DataHelper.getProductResponseMock(id);

		when(productRequestMapper.toProduct(productRequestMock)).thenReturn(expected);
		when(productCreateUseCase.execute(expected)).thenReturn(expectedWithId);
		when(productResponseMapper.toProductResponse(expectedWithId)).thenReturn(orderResponseMock);

		ProductResponse productResponse = productController.create(productRequestMock);
		verify(productRequestMapper, times(1)).toProduct(any());
		verify(productCreateUseCase, times(1)).execute(any());
		verify(productResponseMapper, times(1)).toProductResponse(any(Product.class));
		assertNotNull(productResponse);
		assertNotNull(productResponse.getId());
		assertEquals(id, productResponse.getId());
	}

	@Test
	void shouldUpdate() {
		ProductRequest productRequestMock = DataHelper.getProductRequestMock();
		Product expected = DataHelper.getProductMock(null);
		Long id = 1L;
		Product expectedWithId = DataHelper.getProductMock(id);
		ProductResponse orderResponseMock = DataHelper.getProductResponseMock(id);

		when(productRequestMapper.toProduct(productRequestMock)).thenReturn(expected);
		when(productUpdateUseCase.execute(any(Product.class))).thenReturn(expectedWithId);
		when(productResponseMapper.toProductResponse(expectedWithId)).thenReturn(orderResponseMock);

		ProductResponse productResponse = productController.update(id, productRequestMock);

		verify(productRequestMapper, times(1)).toProduct(any());
		verify(productUpdateUseCase, times(1)).execute(any());
		verify(productResponseMapper, times(1)).toProductResponse(any(Product.class));
		assertNotNull(productResponse);
		assertNotNull(productResponse.getId());
		assertEquals(id, productResponse.getId());
	}

	@Test
	void shouldRemove() {
		Long id = 1L;

		doNothing().when(productRemoveUseCase).execute(any());

		productController.remove(id);
		verify(productRemoveUseCase, times(1)).execute(any());
	}

	@Test
	void shouldListByCategory() {
		Long id = 1L;
		List<Product> expectedsWithId = List.of(DataHelper.getProductMock(id));
		List<ProductResponse> ordersResponseMock = List.of(DataHelper.getProductResponseMock(id));

		when(productListUseCase.execute(any(ProductCategory.class))).thenReturn(expectedsWithId);
		when(productResponseMapper.toProductResponse(expectedsWithId)).thenReturn(ordersResponseMock);

		List<ProductResponse> productsResponse = productController.list(ProductCategory.LANCHE.name());

		verify(productListUseCase, times(1)).execute(any());
		verify(productResponseMapper, times(1)).toProductResponse(anyList());
		assertNotNull(productsResponse);
		assertFalse(productsResponse.isEmpty());
	}

	@Test
	void shouldList() {
		List<String> productsCategoryExpected = Arrays.stream(ProductCategory.values()).map(ProductCategory::name).toList();

		when(categoryListUseCase.execute()).thenReturn(productsCategoryExpected);

		List<String> productsCategory = productController.list();

		verify(categoryListUseCase, times(1)).execute();
		assertNotNull(productsCategory);
		assertFalse(productsCategory.isEmpty());
	}

}

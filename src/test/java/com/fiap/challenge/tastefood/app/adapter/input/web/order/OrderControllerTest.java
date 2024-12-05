package com.fiap.challenge.tastefood.app.adapter.input.web.order;

import com.fiap.challenge.tastefood.app.adapter.input.web.order.dto.OrderRequest;
import com.fiap.challenge.tastefood.app.adapter.input.web.order.dto.OrderResponse;
import com.fiap.challenge.tastefood.app.adapter.input.web.order.mapper.OrderRequestMapper;
import com.fiap.challenge.tastefood.app.adapter.input.web.order.mapper.OrderResponseMapper;
import com.fiap.challenge.tastefood.core.domain.Order;
import com.fiap.challenge.tastefood.core.domain.enums.OrderStatus;
import com.fiap.challenge.tastefood.core.usecases.order.*;
import com.fiap.challenge.tastefood.core.usecases.status.StatusListUseCase;
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

public class OrderControllerTest {

	@Mock
	private OrderCreateUseCase orderCreateUseCase;
	@Mock
	private OrderGetUseCase orderGetUseCase;
	@Mock
	private OrderListUseCase orderListUseCase;
	@Mock
	private OrderUpdateStatusUseCase orderUpdateStatusUseCase;
	@Mock
	private StatusListUseCase statusListUseCase;
	@Mock
	private OrderCheckoutUseCase orderCheckoutUseCase;
	@Mock
	private OrderQueueListUseCase orderQueueListUseCase;

	private final OrderRequestMapper orderRequestMapper= mock(OrderRequestMapper.class);
	private final OrderResponseMapper orderResponseMapper = mock(OrderResponseMapper.class);

	@InjectMocks
	private OrderController orderController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void shouldSave() {
		OrderRequest orderRequestMock = DataHelper.getOrderRequestMock();
		Order expected = DataHelper.getOrderMock(null);
		Long id = 1L;
		Order expectedWithId = DataHelper.getOrderMock(id);
		OrderResponse orderResponseMock = DataHelper.getOrderResponseMock(id);


		when(orderRequestMapper.toOrder(orderRequestMock)).thenReturn(expected);
		when(orderCreateUseCase.execute(expected)).thenReturn(expectedWithId);
		when(orderResponseMapper.toOrderResponse(expectedWithId)).thenReturn(orderResponseMock);

		OrderResponse orderResponse = orderController.create(orderRequestMock);
		verify(orderRequestMapper, times(1)).toOrder(any());
		verify(orderCreateUseCase, times(1)).execute(any());
		verify(orderResponseMapper, times(1)).toOrderResponse(any(Order.class));
		assertNotNull(orderResponse);
		assertNotNull(orderResponse.getId());
		assertEquals(id, orderResponse.getId());
	}

	@Test
	void shouldFindById() {
		Long id = 1L;
		Order expectedWithId = DataHelper.getOrderMock(id);
		OrderResponse orderResponseMock = DataHelper.getOrderResponseMock(id);

		when(orderGetUseCase.execute(id)).thenReturn(expectedWithId);
		when(orderResponseMapper.toOrderResponse(expectedWithId)).thenReturn(orderResponseMock);

		OrderResponse orderResponse = orderController.get(id);
		verify(orderGetUseCase, times(1)).execute(any());
		verify(orderResponseMapper, times(1)).toOrderResponse(any(Order.class));
		assertNotNull(orderResponse);
		assertNotNull(orderResponse.getId());
		assertEquals(id, orderResponse.getId());
	}

	@Test
	void shouldListByStatus() {
		OrderStatus orderStatus = OrderStatus.CRIADO;
		Long id = 1L;
		List<Order> expectedsWithId = List.of(DataHelper.getOrderMock(id));
		List<OrderResponse> ordersResponseMock = List.of(DataHelper.getOrderResponseMock(id));

		when(orderListUseCase.execute(orderStatus)).thenReturn(expectedsWithId);
		when(orderResponseMapper.toOrderResponse(expectedsWithId)).thenReturn(ordersResponseMock);

		List<OrderResponse> ordersResponse = orderController.list(orderStatus);
		verify(orderListUseCase, times(1)).execute(any(OrderStatus.class));
		verify(orderResponseMapper, times(1)).toOrderResponse(anyList());
		assertNotNull(ordersResponse);
		assertFalse(ordersResponse.isEmpty());
	}

	@Test
	void shouldUpdateStatus() {
		OrderStatus orderStatus = OrderStatus.RECEBIDO;
		Long id = 1L;
		Order expectedWithId = DataHelper.getOrderMock(id);
		OrderResponse orderResponseMock = DataHelper.getOrderResponseMock(id);

		when(orderUpdateStatusUseCase.execute(id, orderStatus)).thenReturn(expectedWithId);
		when(orderResponseMapper.toOrderResponse(expectedWithId)).thenReturn(orderResponseMock);

		OrderResponse orderResponse = orderController.updateStatus(id, orderStatus);
		verify(orderUpdateStatusUseCase, times(1)).execute(anyLong(), any(OrderStatus.class));
		verify(orderResponseMapper, times(1)).toOrderResponse(any(Order.class));
		assertNotNull(orderResponse);
		assertNotNull(orderResponse.getId());
		assertEquals(id, orderResponse.getId());
	}

	@Test
	void shouldListStatus() {
		List<String> expectedsOrderStatus = Arrays.stream(OrderStatus.values()).map(OrderStatus::name).toList();

		when(statusListUseCase.execute()).thenReturn(expectedsOrderStatus);

		List<String> orderStatusResponse = orderController.list();

		verify(statusListUseCase, times(1)).execute();
		assertNotNull(orderStatusResponse);
		assertFalse(orderStatusResponse.isEmpty());
		assertEquals(orderStatusResponse, expectedsOrderStatus);
	}

	@Test
	void shouldCheckout() {
		Long id = 1L;
		Order expectedWithId = DataHelper.getOrderMock(id);
		OrderResponse orderResponseMock = DataHelper.getOrderResponseMock(id);
		orderResponseMock.setStatus(OrderStatus.RECEBIDO);

		when(orderCheckoutUseCase.execute(id)).thenReturn(expectedWithId);
		when(orderResponseMapper.toOrderResponse(expectedWithId)).thenReturn(orderResponseMock);

		OrderResponse orderResponse = orderController.checkout(id);

		verify(orderCheckoutUseCase, times(1)).execute(id);
		verify(orderResponseMapper, times(1)).toOrderResponse(expectedWithId);
		assertNotNull(orderResponse);
		assertNotNull(orderResponse.getId());
		assertEquals(orderResponse.getStatus().name(), OrderStatus.RECEBIDO.name());
		assertEquals(id, orderResponse.getId());
	}

	@Test
	void shouldOrdersQueue() {
		Long id = 1L;
		List<Order> expectedsWithId = List.of(DataHelper.getOrderMock(id));
		List<OrderResponse> ordersResponseMock = List.of(DataHelper.getOrderResponseMock(id));

		when(orderQueueListUseCase.execute(anyList())).thenReturn(expectedsWithId);
		when(orderResponseMapper.toOrderResponse(expectedsWithId)).thenReturn(ordersResponseMock);

		List<OrderResponse> ordersResponse = orderController.ordersQueue();

		verify(orderQueueListUseCase, times(1)).execute(anyList());
		verify(orderResponseMapper, times(1)).toOrderResponse(anyList());
		assertNotNull(ordersResponse);
		assertFalse(ordersResponse.isEmpty());
	}

}

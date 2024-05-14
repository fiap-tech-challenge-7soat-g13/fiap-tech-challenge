package com.fiap.challenge.tastefood.core.application.useCase.order;

import com.fiap.challenge.tastefood.adapter.driver.infra.OrderGateway;
import com.fiap.challenge.tastefood.adapter.driver.infra.ProductGateway;
import com.fiap.challenge.tastefood.core.application.dto.Order;
import com.fiap.challenge.tastefood.core.domain.entity.OrderEntity;
import com.fiap.challenge.tastefood.core.domain.entity.OrderProduct;
import com.fiap.challenge.tastefood.core.domain.entity.Product;
import com.fiap.challenge.tastefood.core.domain.exception.EntityNotFoundException;
import com.fiap.challenge.tastefood.core.domain.mapper.OrderMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class AddItemIntoOrderUseCase {

    private final OrderGateway gateway;
    private final ProductGateway productGateway;
    private final OrderMapper orderMapper;

    public Order execute(Long orderId, Long productId) {

        OrderEntity order = gateway.findbyId(orderId).orElseThrow(EntityNotFoundException::new);
        Product product = productGateway.findById(productId).orElseThrow(EntityNotFoundException::new);

        order.getProducts().add(createOrderProduct(order, product));

        return this.orderMapper.fromOrderEntityToOrderDTO(this.gateway.update(order));
    }

    private OrderProduct createOrderProduct(OrderEntity order, Product product) {

        OrderProduct orderProduct = new OrderProduct();

        orderProduct.setOrder(order);
        orderProduct.setProduct(product);
        orderProduct.setQuantity(1);
        orderProduct.setPrice(product.getPrice());

        return orderProduct;
    }

}

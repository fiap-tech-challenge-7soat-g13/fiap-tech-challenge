package com.fiap.challenge.tastefood.core.application.useCase.order;

import com.fiap.challenge.tastefood.adapter.driver.infra.OrderGateway;
import com.fiap.challenge.tastefood.adapter.driver.infra.ProductGateway;
import com.fiap.challenge.tastefood.core.application.dto.Order;
import com.fiap.challenge.tastefood.core.domain.entity.OrderEntity;
import com.fiap.challenge.tastefood.core.domain.entity.Product;
import com.fiap.challenge.tastefood.core.domain.exception.InvalidDataException;
import com.fiap.challenge.tastefood.core.domain.mapper.OrderMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class AddItemIntoOrderUseCase {

    private final OrderGateway gateway;
    private final ProductGateway productGateway;
    private final OrderMapper orderMapper;

    public Order execute(Long orderId, Long productId) throws InvalidDataException {
        Optional<Product> product = productGateway.findById(productId);
        OrderEntity order = gateway.findbyId(orderId);
        if (product.isPresent()) {
            if (order.getProducts() == null)
                order.setProducts(new ArrayList<>());

            order.getProducts().add(product.get());
            return this.orderMapper.fromOrderEntityToOrderDTO(this.gateway.update(order));
        } else {
            throw new InvalidDataException("Produto não existe!");
        }
    }

}

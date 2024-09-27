package com.fiap.challenge.tastefood.app.adapter.output.persistence.gateway;

import com.fiap.challenge.tastefood.app.adapter.output.externalApis.MercadoPagoClient;
import com.fiap.challenge.tastefood.app.adapter.output.externalApis.dto.CashOutDTO;
import com.fiap.challenge.tastefood.app.adapter.output.externalApis.dto.ItemDTO;
import com.fiap.challenge.tastefood.app.adapter.output.externalApis.dto.MercadoPagoRequestDTO;
import com.fiap.challenge.tastefood.app.adapter.output.externalApis.dto.MercadoPagoResponseDTO;
import com.fiap.challenge.tastefood.app.adapter.output.persistence.entity.OrderEntity;
import com.fiap.challenge.tastefood.app.adapter.output.persistence.entity.OrderProductEntity;
import com.fiap.challenge.tastefood.app.adapter.output.persistence.entity.PaymentEntity;
import com.fiap.challenge.tastefood.app.adapter.output.persistence.mapper.PaymentMapper;
import com.fiap.challenge.tastefood.app.adapter.output.persistence.repository.OrderRepository;
import com.fiap.challenge.tastefood.app.adapter.output.persistence.repository.PaymentRepository;
import com.fiap.challenge.tastefood.core.domain.Payment;
import com.fiap.challenge.tastefood.core.gateways.PaymentGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@AllArgsConstructor
public class PaymentGatewayImpl implements PaymentGateway {

	private final PaymentRepository paymentRepository;
	private final OrderRepository orderRepository;
	private final MercadoPagoClient mercadoPagoClient;
	private final PaymentMapper paymentMapper;

	@Override
	public Payment generateQRCode(Long orderId) {
		OrderEntity orderEntity = orderRepository.findById(orderId).orElseThrow();
		PaymentEntity payment = getPaymentEntity(orderEntity);
		MercadoPagoRequestDTO requestDTO = buildMercadoPagoRequestDTO(orderEntity, payment);
		MercadoPagoResponseDTO responseDTO = mercadoPagoClient.generateQRCode(requestDTO);

		payment.setQrData(responseDTO.getQrData());

		paymentRepository.save(payment);
		return paymentMapper.toPayment(payment);
	}

	private PaymentEntity getPaymentEntity(OrderEntity orderEntity) {
		PaymentEntity payment;
		Optional<PaymentEntity> paymentSearch = paymentRepository.findByOrderId(orderEntity.getId());
		if (paymentSearch.isPresent()) {
			payment = paymentSearch.get();
		} else {
			payment = new PaymentEntity();
			payment.setOrder(orderEntity);
		}
		return payment;
	}

	private MercadoPagoRequestDTO buildMercadoPagoRequestDTO(OrderEntity orderEntity, PaymentEntity payment) {
		List<OrderProductEntity> produtos = orderEntity.getProducts();

		List<ItemDTO> items = new ArrayList<>();
		produtos.forEach((produto) -> {
			items.add(ItemDTO.builder()
					.category(produto.getProduct().getCategory().name())
					.title(String.format("%s - Order: ", produto.getProduct().getDescription(), orderEntity.getId()))
					.description(produto.getProduct().getDescription())
					.unitPrice(produto.getProduct().getPrice())
					.quantity(Objects.nonNull(produto.getQuantity()) ? produto.getQuantity().longValue() : 1)
					.unitMeasure("unit")
					.totalAmount(produto.getPrice())
					.build()
			);
		});

		return MercadoPagoRequestDTO.builder()
				.externalReference(orderEntity.getId().toString())
				.title(String.format("Order: %s", orderEntity.getId()))
				.description(String.format("Payment: %s - Order: %s", payment.getId(), orderEntity.getId()))
				.totalAmount(payment.getTotal().multiply(BigDecimal.valueOf(2L)))
				.items(items)
				.cashOut(CashOutDTO.builder().amount(payment.getTotal()).build())
				.build();
	}

}

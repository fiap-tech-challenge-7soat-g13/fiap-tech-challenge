package com.fiap.challenge.tastefood.app.adapter.output.externalApis;

import com.fiap.challenge.tastefood.app.adapter.output.externalApis.dto.MercadoPagoRequestDTO;
import com.fiap.challenge.tastefood.app.adapter.output.externalApis.dto.MercadoPagoResponseDTO;
import com.fiap.challenge.tastefood.app.adapter.output.externalApis.exceptions.MercadoPagoAPIException;
import com.fiap.challenge.tastefood.app.configuration.ClientHttpConfig;
import com.fiap.challenge.tastefood.app.configuration.RetryTemplateFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class MercadoPagoClient implements QRClient {

	private final String ACCESS_TOKEN = "Bearer APP_USR-2659235849140414-092818-e0a76741f123f3849e32f2af69c3d644-89659806";
	private final String MERCADO_PAGO_URL = "https://api.mercadopago.com";
	private final String CAIXA_PAGAMENTO_ID = "CAIXA001";
	private final String USER_ID = "89659806";

	private final ObjectMapper objectMapper;

	@Override
	public MercadoPagoResponseDTO generateQRCode(MercadoPagoRequestDTO dto) {
		RetryTemplate retryTemplate = RetryTemplateFactory.retryTemplate();
		RestTemplate restTemplate = new RestTemplate(ClientHttpConfig.clientHttpRequestFactory());

		try {
			ResponseEntity<MercadoPagoResponseDTO> responseEntity = retryTemplate
					.execute(retryContext -> restTemplate.postForEntity(
							String.format("%s%s", MERCADO_PAGO_URL, getQRCodeUrl()),
							httpEntity(dto),
							MercadoPagoResponseDTO.class
					));

			return responseEntity.getBody();
		} catch (RestClientException | JsonProcessingException exception) {
			throw new MercadoPagoAPIException("Error in generate QRCode: " + exception.getMessage());
		}
	}

	private HttpEntity<String> httpEntity(MercadoPagoRequestDTO dto) throws JsonProcessingException {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", ACCESS_TOKEN);

		return new HttpEntity<>(objectMapper.writeValueAsString(dto), headers);
	}

	private String getQRCodeUrl() {
		return String.format("/instore/orders/qr/seller/collectors/%s/pos/%s/qrs",
				USER_ID, CAIXA_PAGAMENTO_ID
		);
	}
}

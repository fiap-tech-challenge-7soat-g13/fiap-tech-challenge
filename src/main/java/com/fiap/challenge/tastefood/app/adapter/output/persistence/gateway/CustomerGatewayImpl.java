package com.fiap.challenge.tastefood.app.adapter.output.persistence.gateway;

import com.fiap.challenge.tastefood.app.adapter.output.persistence.entity.CustomerEntity;
import com.fiap.challenge.tastefood.app.adapter.output.persistence.mapper.CustomerMapper;
import com.fiap.challenge.tastefood.app.adapter.output.persistence.repository.CustomerRepository;
import com.fiap.challenge.tastefood.app.configuration.CognitoHelper;
import com.fiap.challenge.tastefood.app.configuration.CognitoJWTParser;
import com.fiap.challenge.tastefood.core.domain.Customer;
import com.fiap.challenge.tastefood.core.gateways.CustomerGateway;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class CustomerGatewayImpl implements CustomerGateway {

	private final CustomerMapper mapper;
	private final CustomerRepository repository;
	private final CognitoHelper helper;

	@Transactional
	public Customer save(Customer customer) {
		CustomerEntity customerEntity = mapper.toCustomerEntity(customer);
		CustomerEntity customerSave = repository.save(customerEntity);

		return mapper.toCustomer(customerSave);
	}

	public Optional<Customer> findById(Long id) {
		Optional<CustomerEntity> customerEntity = repository.findById(id);
		return customerEntity.isPresent() ? customerEntity.map(mapper::toCustomer) : Optional.empty();
	}

	public List<Customer> findAll() {
		List<CustomerEntity> customerList = repository.findAll();
		return customerList.isEmpty() ? List.of() : mapper.toCustomer(customerList);
	}

	@Transactional
	public List<Customer> findByDocument(String document) {
		List<CustomerEntity> customerList = repository.findByDocument(document);
		return customerList.isEmpty() ? List.of() : mapper.toCustomer(customerList);
	}

	@Transactional
	public Customer saveAdmin(String email, String password) {
		boolean success = helper.signUpUser(email, password);
		if (success) {
			return Customer.builder()
					.email(email)
					.build();
		}
		return null;
	}

	public Customer login(String email, String password) {
		String result = helper.validateUser(email, password);
		if (result != null) {
			System.out.println("User is authenticated: " + result);
		} else {
			System.out.println("Username/password is invalid.");
		}

		JSONObject payload = CognitoJWTParser.getPayload(result);
		String provider = payload.get("iss").toString().replace("https://", "");

		com.amazonaws.services.cognitoidentity.model.Credentials credentials =
				helper.getCredentials(provider, result);
		return Customer.builder()
				.sessionToken(credentials.getSessionToken())
				.email(email)
				.password(password)
				.build();
	}

}

package com.fiap.challenge.tastefood.app.configuration;

import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

public class ClientHttpConfig {

	public static ClientHttpRequestFactory clientHttpRequestFactory() {
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		factory.setConnectionRequestTimeout(10000);
		factory.setConnectTimeout(6000);
		return factory;
	}

	public static ClientHttpRequestFactory bufferingClientHttpRequestFactory() {
		ClientHttpRequestFactory factory =
				new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory());
		return factory;
	}

}

package com.fiap.challenge.tastefood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class TasteFoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(TasteFoodApplication.class, args);
	}

}


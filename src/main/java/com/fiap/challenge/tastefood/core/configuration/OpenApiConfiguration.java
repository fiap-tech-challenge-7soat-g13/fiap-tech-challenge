package com.fiap.challenge.tastefood.core.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Taste Food API", version = "0.0.1-SNAPSHOT", description = "API to manage food products"))
public class OpenApiConfiguration {

}

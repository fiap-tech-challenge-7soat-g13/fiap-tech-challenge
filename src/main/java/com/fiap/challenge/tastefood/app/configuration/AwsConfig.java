package com.fiap.challenge.tastefood.app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.services.lambda.LambdaClient;

@Configuration
@Profile("!test")
public class AwsConfig {

    @Bean
    public LambdaClient lambdaClient() {
        return LambdaClient.builder().build();
    }

}

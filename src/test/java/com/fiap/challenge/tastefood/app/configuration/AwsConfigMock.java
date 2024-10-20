package com.fiap.challenge.tastefood.app.configuration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.services.lambda.LambdaClient;

@Configuration
@Profile("test")
public class AwsConfigMock {

    @Bean
    public LambdaClient lambdaClient() {
        return Mockito.mock(LambdaClient.class);
    }

}

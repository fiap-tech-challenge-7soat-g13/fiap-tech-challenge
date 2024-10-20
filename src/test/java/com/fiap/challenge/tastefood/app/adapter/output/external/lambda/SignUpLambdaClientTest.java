package com.fiap.challenge.tastefood.app.adapter.output.external.lambda;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.InvokeRequest;
import software.amazon.awssdk.services.lambda.model.InvokeResponse;

@SpringBootTest
class SignUpLambdaClientTest {

    @MockBean
    private LambdaClient lambdaClient;

    @Autowired
    private SignUpLambdaClient signUpLambdaClient;

    @Test
    void shouldNotThrowAnyException() {

        InvokeResponse invokeResponse = Mockito.mock(InvokeResponse.class);
        SdkBytes sdkBytes = Mockito.mock(SdkBytes.class);

        Mockito.when(lambdaClient.invoke(Mockito.any(InvokeRequest.class))).thenReturn(invokeResponse);
        Mockito.when(invokeResponse.payload()).thenReturn(sdkBytes);
        Mockito.when(sdkBytes.asUtf8String()).thenReturn("{}");

        signUpLambdaClient.signUp("bill.gates@microsoft.com", "abc@123A");

        Assertions.assertTrue(true);
    }

}
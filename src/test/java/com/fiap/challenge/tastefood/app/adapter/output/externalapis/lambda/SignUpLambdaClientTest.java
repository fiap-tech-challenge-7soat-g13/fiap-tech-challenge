package com.fiap.challenge.tastefood.app.adapter.output.externalapis.lambda;

import org.junit.jupiter.api.Test;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.InvokeRequest;
import software.amazon.awssdk.services.lambda.model.InvokeResponse;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SignUpLambdaClientTest {

    private final LambdaClient lambdaClient = mock(LambdaClient.class);

    private final SignUpLambdaClient signUpLambdaClient = new SignUpLambdaClient(lambdaClient);

    @Test
    void shouldNotThrowAnyException() {

        InvokeResponse invokeResponse = mock(InvokeResponse.class);
        SdkBytes sdkBytes = mock(SdkBytes.class);

        when(lambdaClient.invoke(any(InvokeRequest.class))).thenReturn(invokeResponse);
        when(invokeResponse.payload()).thenReturn(sdkBytes);
        when(sdkBytes.asUtf8String()).thenReturn("{}");

        assertDoesNotThrow(() -> signUpLambdaClient.signUp("bill.gates@microsoft.com", "abc@123A"));
    }

}
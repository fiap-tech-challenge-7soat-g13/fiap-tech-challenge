package com.fiap.challenge.tastefood.app.adapter.output.externalApis.lambda;

import com.fiap.challenge.tastefood.app.adapter.output.externalApis.SignUpClient;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.InvokeRequest;
import software.amazon.awssdk.services.lambda.model.InvokeResponse;

@Slf4j
@Service
@AllArgsConstructor
public class SignUpLambdaClient implements SignUpClient {

    private final LambdaClient lambdaClient;

    @Override
    public void signUp(String email, String password) {

        JsonObject payload = new JsonObject();

        payload.addProperty("email", email);
        payload.addProperty("password", password);

        InvokeRequest request = InvokeRequest.builder()
                .functionName("auth-sign-up")
                .payload(SdkBytes.fromUtf8String(payload.toString()))
                .build();

        InvokeResponse response = lambdaClient.invoke(request);

        JsonObject jsonResponse = new Gson().fromJson(response.payload().asUtf8String(), JsonObject.class);

        log.info("Response received from auth-sign-up function: [status={},body={}]", response.statusCode(), jsonResponse);
    }

}

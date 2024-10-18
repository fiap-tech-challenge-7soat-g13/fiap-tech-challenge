package com.fiap.challenge.tastefood.app.adapter.output.external.mock;

import com.fiap.challenge.tastefood.app.adapter.output.external.SignUpClient;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("test")
public class SignUpMockClient implements SignUpClient {

    @Override
    public void signUp(String email, String password) {
        // This is a mock class, it does nothing.
    }

}

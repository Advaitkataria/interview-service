package org.example.interviewservice.feign;

import org.springframework.stereotype.Service;

@Service
public class AuthValidationService {

    private final AuthClient authClient;

    public AuthValidationService(AuthClient authClient) {
        this.authClient = authClient;
    }

    public String validateToken(String token) {
        return authClient.validate(token);
    }
}
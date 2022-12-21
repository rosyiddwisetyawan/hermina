package com.api.hermina.model;

public class AuthenticationResponse {
    private final String token;

    public AuthenticationResponse(String jwttoken) {
        this.token = jwttoken;
    }
    public String getToken() {
        return token;
    }
}

package com.orderservice.app.feign.filter;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class JwtAuthentication extends AbstractAuthenticationToken {
    private String username;

    public JwtAuthentication(String username) {
        super(null); // No authorities yet
        this.username = username;
        setAuthenticated(true); // Mark as authenticated
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.username;
    }
}

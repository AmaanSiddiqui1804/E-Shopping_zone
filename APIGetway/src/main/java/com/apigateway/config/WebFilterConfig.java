package com.apigateway.config;

import com.apigateway.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.web.server.WebFilter;

//@Configuration
public class WebFilterConfig {

    @Bean
    public WebFilter headerModificationFilter() {
        return new JwtAuthenticationFilter();
    }
}
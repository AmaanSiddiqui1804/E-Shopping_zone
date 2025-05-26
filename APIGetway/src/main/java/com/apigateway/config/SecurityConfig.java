package com.apigateway.config;


import com.apigateway.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("userservice/user/getAllUsers").hasAnyRole("ROLE_ADMIN")    // Only admin can access this endpoint
                        .pathMatchers("/userservice/auth/register", "/userservice/auth/login", "userService/auth/**").permitAll() // Allow access without authentication
                        .pathMatchers("/userservice/user/**").hasAnyRole("ROLE_ADMIN", "ROLE_MERCHANT", "ROLE_CUSTOMER")
                        .pathMatchers("/productservice/products/getAllProducts").hasAnyRole("ROLE_CUSTOMER", "ROLE_ADMIN")
                        .pathMatchers("/productservice/products/**", "/notificationservice/notification/**").hasAnyRole("ROLE_ADMIN", "ROLE_MERCHANT")
                        .pathMatchers("/cartservice/carts/**").hasAnyRole("ROLE_MERCHANT", "ROLE_ADMIN", "ROLE_CUSTOMER")
                        .pathMatchers("/orderservice/orders/placeOrder", "/orderservice/orders/customer/**", "/orderservice/orders/cancelOrder/**").hasAnyRole("ROLE_CUSTOMER", "ROLE_ADMIN")
                        .pathMatchers("orderservice/orders/getAllOrders", "/orderservice/orders/changeOrderStatus/**").hasAnyRole("ROLE_MERCHANT", "ROLE_ADMIN")
                        .pathMatchers("/orderservice/**").hasAnyRole("ROLE_ADMIN")
                        .pathMatchers("/cartservice/**").hasAnyRole("ROLE_CUSTOMER", "ROLE_ADMIN")
                        .anyExchange().authenticated()              // All other routes require authentication
                )
                .addFilterAt(jwtAuthenticationFilter, SecurityWebFiltersOrder.AUTHENTICATION) // Add JWT filter
                .csrf().disable()  // Disable CSRF for stateless APIs
                .build();
    }
}

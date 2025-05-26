package com.notificationservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("E-Shopping Zone API")
                        .description("This API handles product management, user registration, and order processing.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Amaan Siddiqui")
                                .email("amaan@example.com")
                                .url("https://github.com/amaansiddiqui1804"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")));
    }
}


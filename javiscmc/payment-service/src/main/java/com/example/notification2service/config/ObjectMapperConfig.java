package com.example.notification2service.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ObjectMapperConfig {
    @Bean
    @Primary
    public ObjectMapper scmsObjectMapper() {
        ObjectMapper responseMapper = new ObjectMapper();
        return responseMapper;
    }
}

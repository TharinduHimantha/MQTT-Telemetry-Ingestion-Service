package com.kernelx.mqttSubscriber.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tools.jackson.databind.ObjectMapper;

@Configuration
public class CoreConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
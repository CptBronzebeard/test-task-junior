package com.justai.testtaskjunior.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

import java.util.Random;


@Configuration
@PropertySource("classpath:/vk/api.properties")
@PropertySource("classpath:/vk/paths.properties")
@PropertySource("classpath:/vk/bot.properties")
public class AppConfig {
    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public Logger getExceptionLogger() {
        return LoggerFactory.getLogger("exceptionLogger");
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Random getRandom() {
        return new Random();
    }
}

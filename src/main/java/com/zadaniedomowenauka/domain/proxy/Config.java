package com.zadaniedomowenauka.domain.proxy;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.codec.Decoder;
import feign.jackson.JacksonDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {

    @Bean
    public Decoder feignDecoder(){
        return new JacksonDecoder();
    }

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}

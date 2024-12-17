package com.ex.smt.Config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ModelMapper mapper() {
        return new ModelMapper();
    }

    public static final String notFoudMessage = "Id is invalid or not found";
}

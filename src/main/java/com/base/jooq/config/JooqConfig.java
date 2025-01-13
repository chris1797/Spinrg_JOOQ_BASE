package com.base.jooq.config;

import org.springframework.boot.autoconfigure.jooq.DefaultConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JooqConfig {

    @Bean
    public DefaultConfigurationCustomizer defaultConfigurationCustomizer() {
        return configuration -> configuration.settings().withRenderSchema(false);
    }
}

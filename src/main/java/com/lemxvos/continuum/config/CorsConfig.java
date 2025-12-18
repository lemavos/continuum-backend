package com.lemxvos.continuum.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/user/**")      // endpoints que liberam CORS
                        .allowedOrigins("http://localhost:5500") // onde vai rodar seu HTML
                        .allowedMethods("GET", "POST")
                        .allowedHeaders("*");
            }
        };
    }
}

package com.jidnivai.sdcian.sdcian.config;

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
                registry.addMapping("/**")
                        // .allowedOrigins("**") // Allow all
                        .allowedOrigins(
                            "http://localhost:4200",
                            "http://192.168.1.9:4200/",
                            "https://jidni911.github.io"
                            )
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*") // Allow all headers
                        .allowCredentials(true);
            }
        };
    }
}

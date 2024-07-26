package com.example.bookmanagement.configs;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for setting up CORS (Cross-Origin Resource Sharing) in the application.
 * 
 * This class configures the CORS settings to allow cross-origin requests from any origin with specified HTTP methods.
 * It also exposes the Authorization header and sets a maximum age for preflight requests.
 * 
 */
@Configuration
@EntityScan("com.example.bookmanagement.entities")
@EnableJpaRepositories("com.example.bookmanagement.repositories")
public class CorsConfig {

    /**
     * Configures CORS settings for the application.
     *
     * This method returns a {@link WebMvcConfigurer} bean that specifies the CORS mappings for the application.
     * It allows all origins with specified HTTP methods (GET, POST, PUT, DELETE), exposes the Authorization header,
     * and sets the maximum age of the preflight request to 3600 seconds.
     * 
     *
     * @return a {@link WebMvcConfigurer} instance with the defined CORS settings
     */
    @Bean
    public WebMvcConfigurer corsConfigure() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOriginPatterns("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .exposedHeaders("Authorization")
                        .maxAge(3600);
            }
        };
    }
}
package com.example.bookmanagement.configs;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Configuration class for Swagger.
 * This class configures Swagger for API documentation in the application.
 */
@Configuration
@EnableWebMvc
public class SwaggerConfig implements WebMvcConfigurer {

    /**
     * Configures the Swagger Docket bean.
     * 
     * @return a configured Docket instance for Swagger 2 documentation.
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.example.bookmanagement"))
                .paths(PathSelectors.regex("/.*"))
                .build().apiInfo(apiInfoMetaData());
    }

    /**
     * Provides API information for Swagger.
     * 
     * @return an ApiInfo instance containing metadata for the API.
     */
    private ApiInfo apiInfoMetaData() {
        return new ApiInfoBuilder()
                .title("Book Management API")
                .description("API documentation for the Book Management application.")
                .version("1.0.0")
                .build();
    }
}
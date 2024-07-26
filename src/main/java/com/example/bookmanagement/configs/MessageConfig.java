package com.example.bookmanagement.configs;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.example.bookmanagement.utils.Constants;

/**
 * Configuration class for setting up message sources for internationalization (i18n).
 *
 * This class configures a {@link ResourceBundleMessageSource} bean to handle message resource bundles
 * used for internationalization. It sets the basename for the message resource bundles and the default encoding.
 * 
 */
@Configuration
public class MessageConfig {

    /**
     * Creates and configures a {@link ResourceBundleMessageSource} bean.
     * 
     * This method initializes a {@link ResourceBundleMessageSource} instance with the specified basename and encoding.
     * The basename is used to locate the message resource bundles, and the encoding specifies how to read the bundle files.
     * 
     *
     * @return a {@link ResourceBundleMessageSource} instance configured with the basename and encoding from {@link Constants}
     */
    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasename(Constants.MESSAGE_SOURCE_BASENAME); 
        source.setDefaultEncoding(Constants.MESSAGE_SOURCE_DEFAULT_ENCODING);
        return source;
    }
}
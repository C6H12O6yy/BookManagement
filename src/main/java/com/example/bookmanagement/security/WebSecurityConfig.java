package com.example.bookmanagement.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.bookmanagement.configs.AppConfig;
import com.example.bookmanagement.entities.EPermission;
import com.example.bookmanagement.exception.CustomAccessDeniedHandler;
import com.example.bookmanagement.jwt.JwtAuthenticationFilter;
import com.example.bookmanagement.utils.RoleConstant;

import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Configuration class for setting up Spring Security.
 * Configures authentication, authorization, and custom filters for the
 * application.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    @Qualifier("delegatedAuthenticationEntryPoint")
    private AuthenticationEntryPoint authEntryPoint;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    /**
     * Bean for the JWT authentication filter.
     *
     * @return a new instance of {@link JwtAuthenticationFilter}
     */
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    /**
     * Configures the global authentication manager with the user details service
     * and password encoder.
     *
     * @param auth the {@link AuthenticationManagerBuilder} instance
     * @throws Exception if an error occurs during configuration
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        AppConfig appConfig = new AppConfig();
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(appConfig.passwordEncoder());
    }

    /**
     * Bean for the authentication manager.
     *
     * @param authenticationConfiguration the {@link AuthenticationConfiguration}
     *                                    instance
     * @return the {@link AuthenticationManager} instance
     * @throws Exception if an error occurs during configuration
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

   /**
 * Configures the {@link SecurityFilterChain} for the application.
 *
 * This method sets up the security filter chain with the following configurations:
 * - Disables CSRF protection.
 * - Configures authorization rules for various endpoints based on permissions defined in {@link EPermission}.
 * - Allows unrestricted access to authentication and Swagger-related endpoints.
 * - Requires authentication for all other requests.
 * - Configures custom exception handling for authentication and access denial.
 * 
 * Adds a {@link JwtAuthenticationFilter} before the {@link UsernamePasswordAuthenticationFilter}.
 *
 * @param http the {@link HttpSecurity} object to configure.
 * @return the configured {@link SecurityFilterChain}.
 * @throws Exception if an error occurs during configuration.
 */
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable())
            .authorizeRequests(requests -> {
                for (EPermission permission : RoleConstant.LIST_PERMISSIONS) {
                    requests.mvcMatchers(HttpMethod.valueOf(permission.getMethod()), permission.getEndpoint())
                            .hasAuthority(permission.name());
                }
                requests
                        .antMatchers("/auth/signin","/auth/login").permitAll()
                        .antMatchers("/swagger-ui/**", "/v2/**", "/authenticate", "/swagger-resources/**").permitAll()
                        .anyRequest().authenticated();
            })
            .exceptionHandling(handling -> {
                handling
                        .authenticationEntryPoint(authEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler);
            });
    return http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class).build();
}


    
}
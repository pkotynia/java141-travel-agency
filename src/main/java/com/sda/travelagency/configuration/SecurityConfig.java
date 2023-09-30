package com.sda.travelagency.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry
                        -> authorizationManagerRequestMatcherRegistry
                        .requestMatchers(HttpMethod.DELETE).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT).hasRole("ADMIN")
                        .requestMatchers("/hotels/**").hasAnyAuthority("USER","ADMIN")
                        .anyRequest().authenticated());
        return httpSecurity.build();
    }
}

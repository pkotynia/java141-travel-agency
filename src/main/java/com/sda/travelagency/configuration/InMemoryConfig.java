package com.sda.travelagency.configuration;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
@EnableWebSecurity
@Profile("test")
public class InMemoryConfig {

    @Bean
    public UserDetailsManager userDetailsManager(){
        return new InMemoryUserDetailsManager();
    }
    @Bean
    public InitializingBean initializingBean(){
        return () -> {
            UserDetails user = User
                    .builder()
                    .username("testAdmin")
                    .passwordEncoder(password -> PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(password))
                    .password("password")
                    .roles("ADMIN")
                    .build();
            userDetailsManager().createUser(user);
            UserDetails testUser = User
                    .builder()
                    .username("testUser")
                    .passwordEncoder(password -> PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(password))
                    .password("password")
                    .roles("USER")
                    .build();
            userDetailsManager().createUser(testUser);
        };
    }

}

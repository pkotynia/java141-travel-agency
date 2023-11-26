package com.sda.travelagency.controller;

import com.sda.travelagency.dtos.AccountCreationDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class AccountControllerTest {
    @Autowired
    private WebTestClient testClient;
    @Autowired
    private UserDetailsManager userDetailsManager;
    @Test
    void shouldCreateUser() {
        testClient
                .post()
                .uri("/users/create")
                .bodyValue(new AccountCreationDto("newUser", "Test1234"))
                .exchange()
                .expectStatus().isCreated();
    }
    @Test
    void shouldNotCreateAccountWithExistingUserName() {
        UserDetails user = User
                .withUsername("existingUser")
                .password("Test1234")
                .roles("USER")
                .build();
        userDetailsManager.createUser(user);
        testClient
                .post()
                .uri("/users/create")
                .bodyValue(new AccountCreationDto("existingUser", "Test1234"))
                .exchange()
                .expectStatus().isBadRequest();
    }
    @Test
    void shouldNotCreateAccountWithIncorrectPassword() {
        testClient
                .post()
                .uri("/users/create")
                .bodyValue(new AccountCreationDto("testAccount", "password"))
                .exchange()
                .expectStatus().isBadRequest();
    }
    @Test
    void shouldCreateAdmin() {
        testClient
                .post()
                .uri("/users/admin/create")
                .bodyValue(new AccountCreationDto("newAdminByAdmin", "Test1234"))
                .headers(headersConsumer -> headersConsumer.setBasicAuth("testAdmin", "password"))
                .exchange()
                .expectStatus().isCreated();
    }
    @Test
    void shouldNotCreateAdminByUser() {
        testClient
                .post()
                .uri("/users/admin/create")
                .bodyValue(new AccountCreationDto("newAdminByUser", "Test1234"))
                .headers(headersConsumer -> headersConsumer.setBasicAuth("testUser", "password"))
                .exchange()
                .expectStatus().isForbidden();
    }

    @Test
    void shouldDeleteUser() {
        UserDetails user = User
                .withUsername("userToDelete")
                .password("Test1234")
                .passwordEncoder(password -> PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(password))
                .roles("USER")
                .build();
        userDetailsManager.createUser(user);
        testClient
                .delete()
                .uri("/users/delete")
                .headers(headersConsumer -> headersConsumer.setBasicAuth("userToDelete", "Test1234"))
                .exchange()
                .expectStatus().isOk();
    }
    @Test
    void shouldPromoteUserToAdminByAdmin() {
        UserDetails user = User
                .withUsername("userToPromote")
                .password("Test1234")
                .passwordEncoder(password -> PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(password))
                .roles("USER")
                .build();
        userDetailsManager.createUser(user);
        testClient
                .put()
                .uri("/users/admin/promoteToAdmin/{username}", user.getUsername())
                .headers(headersConsumer -> headersConsumer.setBasicAuth("testAdmin", "password"))
                .exchange()
                .expectStatus().isOk();
        user = userDetailsManager.loadUserByUsername(user.getUsername());
        Assertions.assertTrue(user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
    }
    @Test
    void shouldNotPromoteUserToAdminByUser() {
        UserDetails user = User
                .withUsername("userNotToPromote")
                .password("Test1234")
                .passwordEncoder(password -> PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(password))
                .roles("USER")
                .build();
        userDetailsManager.createUser(user);
        testClient
                .put()
                .uri("/users/admin/promoteToAdmin/{username}", user.getUsername())
                .headers(headersConsumer -> headersConsumer.setBasicAuth("testUser", "password"))
                .exchange()
                .expectStatus().isForbidden();
        user = userDetailsManager.loadUserByUsername(user.getUsername());
        Assertions.assertFalse(user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
    }

    @Test
    void shouldDemoteAdminToUserByAdmin() {
            UserDetails user = User
                    .withUsername("AdminToDemote")
                    .password("Test1234")
                    .passwordEncoder(password -> PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(password))
                    .roles("USER","ADMIN")
                    .build();
            userDetailsManager.createUser(user);
            testClient
                    .put()
                    .uri("/users/admin/demoteToUser/{username}", user.getUsername())
                    .headers(headersConsumer -> headersConsumer.setBasicAuth("testAdmin", "password"))
                    .exchange()
                    .expectStatus().isOk();
            user = userDetailsManager.loadUserByUsername(user.getUsername());
            Assertions.assertFalse(user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
    }
    @Test
    void shouldNotDemoteAdminToUserByUser() {
        UserDetails user = User
                .withUsername("AdminNotToDemote")
                .password("Test1234")
                .passwordEncoder(password -> PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(password))
                .roles("USER","ADMIN")
                .build();
        userDetailsManager.createUser(user);
        testClient
                .put()
                .uri("/users/admin/demoteToUser/{username}", user.getUsername())
                .headers(headersConsumer -> headersConsumer.setBasicAuth("testUser", "password"))
                .exchange()
                .expectStatus().isForbidden();
        user = userDetailsManager.loadUserByUsername(user.getUsername());
        Assertions.assertTrue(user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
    }

}
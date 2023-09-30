package com.sda.travelagency.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController()
@RequestMapping("/users")
public class RegisterController {

    private final UserDetailsManager userDetailsManager;

    public RegisterController(UserDetailsManager userDetailsManager) {
        this.userDetailsManager = userDetailsManager;
    }

//    @PostMapping("/create")
//    ResponseEntity<String> addUser(@RequestBody UserDetails user) {
//        userDetailsManager.createUser(user);
//        return new ResponseEntity<>("User created", HttpStatus.CREATED);
//    }
}

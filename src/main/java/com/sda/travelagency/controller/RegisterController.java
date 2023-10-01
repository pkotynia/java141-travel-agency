package com.sda.travelagency.controller;

import com.sda.travelagency.dtos.AccountDto;
import com.sda.travelagency.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController()
@RequestMapping("/users")
public class RegisterController {

    private final AccountService accountService;
    public RegisterController(AccountService accountService) {
        this.accountService = accountService;
    }


    @PostMapping("/create")
    ResponseEntity<String> addUser(@Valid @RequestBody AccountDto user) {
        accountService.addUser(user);
        return new ResponseEntity<>("User created", HttpStatus.CREATED);
    }

    @PostMapping("/admin/create")
    ResponseEntity<String> addAdmin(@Valid @RequestBody AccountDto admin) {
        accountService.addAdmin(admin);
        return new ResponseEntity<>("Admin created", HttpStatus.CREATED);
    }
}

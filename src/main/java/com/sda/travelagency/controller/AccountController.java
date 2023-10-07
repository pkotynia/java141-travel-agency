package com.sda.travelagency.controller;

import com.sda.travelagency.dtos.AccountDto;
import com.sda.travelagency.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;


@RestController()
@RequestMapping("/users")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @PostMapping("/create")
    ResponseEntity<String> addUser(@Valid @RequestBody AccountDto user) {
        accountService.createUser(user);
        return new ResponseEntity<>("User created", HttpStatus.CREATED);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/admin/create")
    ResponseEntity<String> addAdmin(@Valid @RequestBody AccountDto admin) {
        accountService.createAdmin(admin);
        return new ResponseEntity<>("Admin created", HttpStatus.CREATED);
    }

    @Secured("ROLE_USER")
    @DeleteMapping("/delete")
    ResponseEntity<String> deleteUser() {
        accountService.deleteUser();
        return new ResponseEntity<>("User deleted", HttpStatus.ACCEPTED);
    }

    @Secured("ROLE_USER")
    @PutMapping("/changePassword")
    ResponseEntity<String> changePassword(@RequestParam String password) {
        accountService.changePassword(password);
        return new ResponseEntity<>("User updated", HttpStatus.ACCEPTED);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/admin/promoteToAdmin/{username}")
    ResponseEntity<String> promoteUserToAdmin(@PathVariable String username) {
        accountService.promoteUserToAdmin(username);
        return new ResponseEntity<>(username + " role promoted to admin", HttpStatus.ACCEPTED);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/admin/demoteToUser/{username}")
    ResponseEntity<String> demoteAdminToUser(@PathVariable String username) {
        accountService.demoteAdminToUser(username);
        return new ResponseEntity<>(username + " role demoted to user", HttpStatus.ACCEPTED);
    }

}
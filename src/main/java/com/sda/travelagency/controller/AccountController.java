package com.sda.travelagency.controller;

import com.sda.travelagency.dtos.AccountDto;
import com.sda.travelagency.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @PostMapping("/create")
    ResponseEntity<String> createUser(@Valid @RequestBody AccountDto user) throws RuntimeException{
        accountService.createUser(user);
        return new ResponseEntity<>("User created", HttpStatus.CREATED);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/admin/create")
    ResponseEntity<String> createAdmin(@Valid @RequestBody AccountDto admin) throws RuntimeException{
        accountService.createAdmin(admin);
        return new ResponseEntity<>("Admin created", HttpStatus.CREATED);
    }

    @Secured("ROLE_USER")
    @DeleteMapping("/delete")
    ResponseEntity<String> deleteUser() {
        accountService.deleteUser();
        return new ResponseEntity<>("Account deleted", HttpStatus.OK);
    }

    @Secured("ROLE_USER")
    @PutMapping("/changePassword")
    ResponseEntity<String> changePassword(@RequestParam String password) throws RuntimeException{
        accountService.changePassword(password);
        return new ResponseEntity<>("Password changed", HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/admin/promoteToAdmin/{username}")
    ResponseEntity<String> promoteUserToAdmin(@PathVariable String username) throws RuntimeException{
        accountService.promoteUserToAdmin(username);
        return new ResponseEntity<>(username + " role promoted to admin", HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/admin/demoteToUser/{username}")
    ResponseEntity<String> demoteAdminToUser(@PathVariable String username) throws RuntimeException{
        accountService.demoteAdminToUser(username);
        return new ResponseEntity<>(username + " role demoted to user", HttpStatus.OK);
    }

}
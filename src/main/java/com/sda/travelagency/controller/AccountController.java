package com.sda.travelagency.controller;

import com.sda.travelagency.dtos.AccountCreationDto;
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
    ResponseEntity<AccountCreationDto> createUser(@Valid @RequestBody AccountCreationDto user) throws RuntimeException{
        accountService.createUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/admin/create")
    ResponseEntity<AccountCreationDto> createAdmin(@Valid @RequestBody AccountCreationDto admin) throws RuntimeException{
        accountService.createAdmin(admin);
        return new ResponseEntity<>(admin, HttpStatus.CREATED);
    }

    @Secured("ROLE_USER")
    @DeleteMapping("/delete")
    ResponseEntity<AccountDto> deleteUser() {
        AccountDto user = accountService.deleteUser();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Secured("ROLE_USER")
    @PutMapping("/changePassword")
    ResponseEntity<AccountDto> changePassword(@RequestParam String password) throws RuntimeException{
        AccountDto accountDto = accountService.changePassword(password);
        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/admin/promoteToAdmin/{username}")
    ResponseEntity<AccountDto> promoteUserToAdmin(@PathVariable String username) throws RuntimeException{
        AccountDto accountDto = accountService.promoteUserToAdmin(username);
        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/admin/demoteToUser/{username}")
    ResponseEntity<AccountDto> demoteAdminToUser(@PathVariable String username) throws RuntimeException{
        AccountDto accountDto = accountService.demoteAdminToUser(username);
        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }

}
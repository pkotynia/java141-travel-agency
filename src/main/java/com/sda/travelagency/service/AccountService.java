package com.sda.travelagency.service;

import com.sda.travelagency.dtos.AccountDto;
import com.sda.travelagency.exception.UserAlreadyExistsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final UserDetailsManager userDetailsManager;

    public AccountService(UserDetailsManager userDetailsManager) {
        this.userDetailsManager = userDetailsManager;
    }

    public void addUser(AccountDto accountDto){
        if(userDetailsManager.userExists(accountDto.getUserName())){
            throw new UserAlreadyExistsException("This username is already taken");
        }
        UserDetails user = User
                .withUsername(accountDto.getUserName())
                .password(accountDto.getPassword())
                .roles("USER")
                .build();
        userDetailsManager.createUser(user);
    }

    public void addAdmin(AccountDto accountDto){
        if(userDetailsManager.userExists(accountDto.getUserName())){
            throw new UserAlreadyExistsException("This username is already taken");
        }
        UserDetails admin = User
                .withUsername(accountDto.getUserName())
                .password(accountDto.getPassword())
                .roles("ADMIN")
                .build();
        userDetailsManager.createUser(admin);
    }
}

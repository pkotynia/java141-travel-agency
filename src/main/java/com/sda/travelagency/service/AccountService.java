package com.sda.travelagency.service;

import com.sda.travelagency.dtos.AccountDto;
import com.sda.travelagency.exception.UserAlreadyExistsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final UserDetailsManager userDetailsManager;

    public AccountService(UserDetailsManager userDetailsManager) {
        this.userDetailsManager = userDetailsManager;
    }

    public void addUser(AccountDto accountDto){
        if(userDetailsManager.userExists(accountDto.getName())){
            throw new UserAlreadyExistsException("This username is already taken");
        }
        String password = new BCryptPasswordEncoder().encode(accountDto.getPassword());
        UserDetails user = User
                .withUsername(accountDto.getName())
                .password(password)
                .roles("USER")
                .build();
        userDetailsManager.createUser(user);
    }

    public void addAdmin(AccountDto accountDto){
        if(userDetailsManager.userExists(accountDto.getName())){
            throw new UserAlreadyExistsException("This username is already taken");
        }
        String password = new BCryptPasswordEncoder().encode(accountDto.getPassword());
        UserDetails admin = User
                .withUsername(accountDto.getName())
                .password(password)
                .roles("ADMIN")
                .build();
        userDetailsManager.createUser(admin);
    }
}

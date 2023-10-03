package com.sda.travelagency.service;

import com.sda.travelagency.dtos.AccountDto;
import com.sda.travelagency.exception.UserAlreadyExistsException;
import com.sda.travelagency.util.Username;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    private final UserDetailsManager userDetailsManager;

    public AccountService(UserDetailsManager userDetailsManager) {
        this.userDetailsManager = userDetailsManager;
    }

    public void createUser(AccountDto accountDto){
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

    public void createAdmin(AccountDto accountDto){
        if(userDetailsManager.userExists(accountDto.getName())){
            throw new UserAlreadyExistsException("This username is already taken");
        }
        String password = new BCryptPasswordEncoder().encode(accountDto.getPassword());
        UserDetails admin = User
                .withUsername(accountDto.getName())
                .password(password)
                .roles("USER","ADMIN")
                .build();
        userDetailsManager.createUser(admin);
    }

    public void deleteUser(){
        userDetailsManager.deleteUser(Username.getActive());
    }

    public void changePassword(String password){
        UserDetails user = userDetailsManager.loadUserByUsername(Username.getActive());
        String newPassword = new BCryptPasswordEncoder().encode(password);
        userDetailsManager.changePassword(user.getPassword(), newPassword);
    }

    public void promoteUserToAdmin(String username){
        if(!userDetailsManager.userExists(username)){
            throw new UsernameNotFoundException("No such user exists");
        }
        UserDetails user = User
                .withUserDetails(userDetailsManager.loadUserByUsername(username))
                .roles("USER","ADMIN")
                .build();
        userDetailsManager.updateUser(user);
    }
    public void demoteAdminToUser(String username){
        if(!userDetailsManager.userExists(username)){
            throw new UsernameNotFoundException("No such user exists");
        }
        UserDetails admin = User
                .withUserDetails(userDetailsManager.loadUserByUsername(username))
                .roles("USER")
                .build();
        userDetailsManager.updateUser(admin);
    }
}

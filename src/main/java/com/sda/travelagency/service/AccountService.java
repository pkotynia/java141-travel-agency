package com.sda.travelagency.service;

import com.sda.travelagency.dtos.AccountDto;
import com.sda.travelagency.exception.AnnonymousAuthorizationException;
import com.sda.travelagency.exception.UserAlreadyExistsException;
import com.sda.travelagency.util.Username;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final UserDetailsManager userDetailsManager;

    public AccountService(UserDetailsManager userDetailsManager) {
        this.userDetailsManager = userDetailsManager;
    }
    /**
     * This method  takes AccountDto object as a param.
     * If username already exists in database it throws UserAlreadyExistsException.
     * Password is encoded by BCryptPasswordEncoder.
     * It is using to User builder build UserDetails object with data from AccountDto. which is saved in UserDetailsManager
     * This method is strictly used to create USER role.
     * @param accountDto
     * @return void
     * @throws UserAlreadyExistsException "This username is already taken"
     **/
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
    /**
     * This method takes AccountDto object as a param.
     * If username already exists in database it thorws UserAlreadyExistsException.
     * Password is encoded by BCryptPasswordEncoder.
     * It is using to User builder build UserDetails object with data from AccountDto. which is saved in UserDetailsManager
     * This method is strictly used to create ADMIN user role.
     * @param accountDto
     * @return void
     * @throws UserAlreadyExistsException "This username is already taken"
     **/
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
    /**
     * This method is used to delete active account.
     * @return void
     **/
    public void deleteUser(){
        userDetailsManager.deleteUser(Username.getActive());
    }
    /**
     * This method takes new password as a param.
     * If present it gets active user username bu Username util class or else it throws SessionExpiredException
     * Instance of active user UserDetails object is loaded from UserDetailsManager,
     * New password is encoded by BCryptPasswordEncoder and changed.
     * @param password
     * @return void
     * @throws AnnonymousAuthorizationException "No user logged in"
     **/
    public void changePassword(String password){
        String username = Username.getActive();
        if(username == null) {
            throw new AnnonymousAuthorizationException("No user logged in");
        }
        UserDetails user = userDetailsManager.loadUserByUsername(username);
        String newPassword = new BCryptPasswordEncoder().encode(password);
        userDetailsManager.changePassword(user.getPassword(), newPassword);
    }
    /**
     * This method takes username as a param.
     * If username dont exists in database it throws UsernameNotFoundException.
     * It is using User builder build UserDetails object with data from existing user and add ADMIN role to user account
     * Next it updates user in UserDetailsManager
     * @param username
     * @return void
     * @throws UsernameNotFoundException "No such user exists"
     **/
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
    /**
     * This method takes username as a param.
     * If username dont exists in database it throws UsernameNotFoundException.
     * It is using User builder build UserDetails object with data from existing user and remove ADMIN role from user account
     * Next it updates user in UserDetailsManager
     * @param username
     * @return void
     * @throws UsernameNotFoundException "No such user exists"
     **/
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

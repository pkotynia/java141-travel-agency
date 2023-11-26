package com.sda.travelagency.mapper;

import com.sda.travelagency.dtos.AccountCreationDto;
import com.sda.travelagency.dtos.AccountDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
@Component
public class AccountMapper {
    public AccountDto UserDetailsToAccountDto(UserDetails userDetails){
        AccountDto accountDto = new AccountDto();
        accountDto.setUserName(userDetails.getUsername());
        accountDto.setRole(userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        return accountDto;
    }
}

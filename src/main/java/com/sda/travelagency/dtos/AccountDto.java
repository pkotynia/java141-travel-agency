package com.sda.travelagency.dtos;

import java.util.List;

public class AccountDto {
    private String userName;
    private List<String> role;

    public String getUserName() {
        return userName;
    }

    public List<String> getRole() {
        return role;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }
}

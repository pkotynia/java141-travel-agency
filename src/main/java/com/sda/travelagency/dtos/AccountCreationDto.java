package com.sda.travelagency.dtos;

import com.sda.travelagency.annotation.JsonElement;
import com.sda.travelagency.annotation.JsonSerializable;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@JsonSerializable
public class AccountCreationDto {
    @JsonElement
    @NotBlank(message = "Account name is mandatory")
    private String name;
    @JsonElement
    @Size(min = 8, max = 32, message = "Password size should be between 8 and 32 characters")
    private String password;

    public AccountCreationDto(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public AccountCreationDto() {
    }

    @AssertTrue(message = "Password should contain at least one lowercase and one uppercase letter" +
            ", one digit and cant contain white spaces")
    private boolean isPasswordCorrect(){
        Pattern pattern = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.* ).*");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

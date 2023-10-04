package com.example.bookstoreapplication.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public class LoginDto {

    @Email
    public String email;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{7,15}$", message = "Invalid password.")
    public String password;

    public LoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

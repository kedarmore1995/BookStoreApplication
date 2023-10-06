package com.example.bookstoreapplication.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class UserDto {
    @Pattern(regexp = "[A-Z][a-z]{2,}", message = "First letter should be capital, with min 3 characters")
    public String firstName;
    @Pattern(regexp = "[A-Z][a-z]{2,}", message = "First letter should be capital, with min 3 characters")
    public String lastName;
    @Email
    public String email;
    @NotEmpty
    public String address;
    @NotNull
    public LocalDate DOB;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{7,15}$",message = "Password Not valid")
    public String password;

}

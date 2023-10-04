package com.example.bookstoreapplication.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class UserDto {
    @NotEmpty
    @Size(min = 3, max= 10)
    public String firstName;
    @NotEmpty
    @Size(min = 3, max= 10)
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

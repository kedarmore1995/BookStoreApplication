package com.example.bookstoreapplication.model;


import com.example.bookstoreapplication.dto.UserDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Data
@NoArgsConstructor
public class User{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public int UserID;
    public String firstName;
    public String lastName;
    public String email;
    public String address;
    public LocalDate DOB;
    public String password;

    public User (UserDto userDto){
        this.firstName=userDto.firstName;
        this.lastName= userDto.lastName;
        this.email= userDto.email;
        this.address= userDto.address;
        this.DOB=userDto.DOB;
        this.password= userDto.password;
    }
}

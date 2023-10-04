package com.example.bookstoreapplication.controller;


import com.example.bookstoreapplication.dto.LoginDto;
import com.example.bookstoreapplication.dto.ResponseDto;
import com.example.bookstoreapplication.dto.UserDto;
import com.example.bookstoreapplication.model.User;
import com.example.bookstoreapplication.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public User registerUser(@Valid @RequestBody UserDto userDto) {
        return userService.registerUser(userDto);
    }

    @GetMapping("/getAll")
    public List<User> showAll() {
        return userService.showAllUser();
    }

    @GetMapping("/getById/{userId}")
    public User getById(@PathVariable int userId) {
        return userService.getbyID(userId);
    }

    @GetMapping("/getByEmailId")
    public User getById(@RequestParam String emailId) {
        return userService.getbyEmailID(emailId);
    }

    @PutMapping("/resetPassword")
    public User forgotPassword(@RequestParam String emailId, @RequestParam String NewPassword) {
        return userService.forgotPassword(emailId, NewPassword);
    }

    @PutMapping("/changePassword")
    public User changePassword(@RequestBody LoginDto loginDto, @RequestParam String NewPassword) {
        return userService.changePassword(loginDto, NewPassword);
    }

    @PutMapping("/updateUserByEmailId")
    public ResponseEntity<ResponseDto> updateUserByEmailId(@RequestParam String emailId,@Valid @RequestBody UserDto userDto) {
        return userService.updateUserByEmailId(emailId, userDto);
    }

    @GetMapping("/login")
    public ResponseEntity<ResponseDto> loginUser(@Valid @RequestBody LoginDto loginDto) {
        return userService.loginUser(loginDto);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ResponseDto> deleteUserById(@PathVariable int userId){
        return userService.deleteUserById(userId);
    }

    @GetMapping("/getByToken/{token}")
    public User getUserByToken(@PathVariable String token){
        return  userService.getUserByToken(token);
    }
}
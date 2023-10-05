package com.example.bookstoreapplication.controller;


import com.example.bookstoreapplication.dto.LoginDto;
import com.example.bookstoreapplication.dto.ResponseDto;
import com.example.bookstoreapplication.dto.UserDto;
import com.example.bookstoreapplication.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDto> registerUser(@Valid @RequestBody UserDto userDto) {
        ResponseDto responseDto = new ResponseDto("New user added.",userService.registerUser(userDto));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<ResponseDto> showAll() {
        ResponseDto responseDto = new ResponseDto("Get all users.",userService.showAllUser());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/getById/{userId}")
    public ResponseEntity<ResponseDto> getById(@PathVariable int userId) {
        ResponseDto responseDto = new ResponseDto("Get user by id."+userId,userService.getbyID(userId));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/getByEmailId")
    public ResponseEntity<ResponseDto> getById(@RequestParam String emailId) {
        ResponseDto responseDto = new ResponseDto("Get user by email."+emailId,userService.getbyEmailID(emailId));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/resetPassword")
    public ResponseEntity<ResponseDto> forgotPassword(@RequestParam String emailId, @RequestParam String NewPassword) {
        ResponseDto responseDto = new ResponseDto("Password Reset Done.",userService.forgotPassword(emailId, NewPassword));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/changePassword")
    public ResponseEntity<ResponseDto> changePassword(@RequestBody LoginDto loginDto, @RequestParam String NewPassword) {
        ResponseDto responseDto = new ResponseDto("Password Changed.",userService.changePassword(loginDto, NewPassword));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
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
    public ResponseEntity<ResponseDto> getUserByToken(@PathVariable String token){
        ResponseDto responseDto = new ResponseDto("Get User by token.",userService.getUserByToken(token));
        return  new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
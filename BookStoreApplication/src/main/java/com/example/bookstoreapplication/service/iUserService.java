package com.example.bookstoreapplication.service;

import com.example.bookstoreapplication.dto.LoginDto;
import com.example.bookstoreapplication.dto.ResponseDto;
import com.example.bookstoreapplication.dto.UserDto;
import com.example.bookstoreapplication.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface iUserService {

    public User registerUser(UserDto userDto);

    public List<User> showAllUser();

    public User getbyID(int userId);

    public User getbyEmailID(String emailId);

    public User forgotPassword(String emailId, String NewPassword);

    public User changePassword(LoginDto loginDto, String NewPassword);
    public ResponseEntity<ResponseDto> updateUserByEmailId(String emailId,UserDto userDto);
    public ResponseEntity<ResponseDto> loginUser(LoginDto loginDto);
    public ResponseEntity<ResponseDto> deleteUserById(int userId);
    public User getUserByToken(String token);
}

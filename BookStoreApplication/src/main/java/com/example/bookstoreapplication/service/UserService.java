package com.example.bookstoreapplication.service;

import com.example.bookstoreapplication.dto.LoginDto;
import com.example.bookstoreapplication.dto.ResponseDto;
import com.example.bookstoreapplication.dto.UserDto;
import com.example.bookstoreapplication.exception.PasswordsNotMatchingException;
import com.example.bookstoreapplication.exception.UserNotFoundException;
import com.example.bookstoreapplication.model.User;
import com.example.bookstoreapplication.repository.UserRepo;
import com.example.bookstoreapplication.util.EmailSenderService;
import com.example.bookstoreapplication.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class UserService implements iUserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    EmailSenderService emailSenderService;

    public User registerUser(UserDto userDto) {
        User user = new User(userDto);
        userRepo.save(user);
        String token = tokenUtil.createToken(user.getUserID());
        System.out.println(token);
        emailSenderService.sendEmail("user@gmail.com", "Registration Done Successfully.", "New user has been added. The name of the person is: " + user.getFirstName() +
                '\n' + "To get the details click the link: http://localhost:8080/user/getByToken/" + token);
        return user;
    }

    @Override
    public List<User> showAllUser() {
        return userRepo.findAll();
    }

    @Override
    public User getbyID(int userId) {
        if (userRepo.existsById(userId)) {
            return userRepo.findById(userId).get();
        } else {
            throw new UserNotFoundException("No such user found with this userId");
        }
    }

    @Override
    public User getbyEmailID(String emailId) {
        User user = userRepo.getUserByEmail(emailId);
        if (user != null) {
            return user;
        } else {
            throw new UserNotFoundException("Search with another Email.");
        }
    }

    //    @Override
//    Put Method
//    if EmailId = emailid
//    reset old password
    @Override
    public User forgotPassword(String emailId, String NewPassword) {
        User user = userRepo.getUserByEmail(emailId);
        if (user != null) {
            user.setPassword(NewPassword);
            userRepo.save(user);
            emailSenderService.sendEmail("user@gmail.com", "Password reset done Successfully.", "The password has been reset.");
            return user;
        } else {
            throw new UserNotFoundException("EmailId Incorrect...!");
        }
    }


//    put
//    update old password with new password

    @Override
    public User changePassword(LoginDto loginDto, String NewPassword) {
        User user = userRepo.getUserByEmail(loginDto.getEmail());
        if (user!=null){
            if (user.getPassword().equals(loginDto.getPassword())) {
                user.setPassword(NewPassword);
                userRepo.save(user);
                emailSenderService.sendEmail("user@gmail.com", "Password changed Successfully.", "The password has been changed successfully.");
                return user;
            } else {
                throw new PasswordsNotMatchingException("Old Password is Incorrect...!");
            }
        }else {
            throw new UserNotFoundException("User not found. Check emailId...!");
        }
    }


//    put
//    update user by emailId
//    if emailid matches update the user details

    @Override
    public ResponseEntity<ResponseDto> updateUserByEmailId(String emailId, UserDto userDto) {
        User user = userRepo.getUserByEmail(emailId);
        if (user != null) {
            user.setPassword(userDto.password);
            user.setDOB(userDto.DOB);
            user.setAddress(userDto.address);
            user.setFirstName(userDto.firstName);
            user.setLastName(userDto.lastName);
            userRepo.save(user);
            emailSenderService.sendEmail("user@gmail.com", "User details update", "The password has been updated successfully.");
            ResponseDto responseDto = new ResponseDto("Put call successful", user);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } else {
            throw new UserNotFoundException("Incorrect EmailId.");
        }
    }

//    get
//    logindto
//    email id and password
//    return that user

    public ResponseEntity<ResponseDto> loginUser(LoginDto loginDto) {
        User user = userRepo.getUserByEmail(loginDto.getEmail());
        if (user != null && user.getPassword().equals(loginDto.getPassword())) {
            ResponseDto responseDto = new ResponseDto("Login Successfull", user);
            return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED);
        } else {
            throw new UserNotFoundException("Either Username or Password is Invalid");
        }
    }

    //      delete
//    if user id matches delete the user
    public ResponseEntity<ResponseDto> deleteUserById(int userId) {
        if (userRepo.existsById(userId)) {
            userRepo.deleteById(userId);
            ResponseDto responseDto = new ResponseDto("User Deleted Successfully", false);
            emailSenderService.sendEmail("user@gmail.com", "User deleted Successfully.", "The user has been deleted.");
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } else {
            throw new UserNotFoundException("UserId Entered is Incorrect.");
        }
    }

    @Override
    public User getUserByToken(String token){
        int userId = tokenUtil.decodeToken(token);
        return userRepo.findById(userId).get();
    }
}

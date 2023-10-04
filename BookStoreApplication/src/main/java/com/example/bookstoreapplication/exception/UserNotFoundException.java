package com.example.bookstoreapplication.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String msg){
        super(msg);
    }
}

package com.example.bookstoreapplication.exception;

public class PasswordsNotMatchingException extends RuntimeException{

    public PasswordsNotMatchingException(String msg){
        super(msg);
    }
}

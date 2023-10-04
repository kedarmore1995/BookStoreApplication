package com.example.bookstoreapplication.exception;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(String msg){
        super(msg);
    }
}

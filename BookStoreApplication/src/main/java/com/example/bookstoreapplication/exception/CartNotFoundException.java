package com.example.bookstoreapplication.exception;

public class CartNotFoundException extends RuntimeException{

    public CartNotFoundException(String msg){
        super(msg);
    }

}

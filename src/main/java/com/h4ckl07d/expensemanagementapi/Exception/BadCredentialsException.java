package com.h4ckl07d.expensemanagementapi.Exception;

public class BadCredentialsException extends RuntimeException{

    public BadCredentialsException(String message){
        super (message);
    }
}

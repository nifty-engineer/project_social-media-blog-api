package com.example.domainstructuring.exception;

public class ExcessiveCharactersException extends RuntimeException{

    public ExcessiveCharactersException(String message) {
        super(message);
    }
    
}
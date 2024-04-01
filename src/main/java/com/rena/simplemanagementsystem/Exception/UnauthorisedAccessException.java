package com.rena.simplemanagementsystem.Exception;


public class UnauthorisedAccessException extends RuntimeException{

    public UnauthorisedAccessException(String message) {
        super(message);
    }
}

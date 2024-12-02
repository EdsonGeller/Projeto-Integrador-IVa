package com.example.integradorIVa.TaskMasters.exception;

public class UsernameUniqueViolationException extends RuntimeException {

    public UsernameUniqueViolationException(String message){
        super(message);
    }
}
